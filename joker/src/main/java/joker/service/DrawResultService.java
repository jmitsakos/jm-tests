package joker.service;

import joker.domain.DrawResult;
import joker.dto.Combination;
import joker.dto.JNumber;
import joker.repository.DrawResultRepository;
import joker.util.Combinations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;

/**
 * Created by imitsakos on 23/7/2015.
 */
@Service
public class DrawResultService {

    @Autowired
    private DrawResultRepository drawResultRepository;

    //@Autowired
    private Combinations combinations = new Combinations();

    public String save(DrawResult drawResult) {

        if(drawResult.getDrawDate() != null
                && validateNumber(drawResult.getN1())
                && validateNumber(drawResult.getN2())
                && validateNumber(drawResult.getN3())
                && validateNumber(drawResult.getN4())
                && validateNumber(drawResult.getN5())
                && validateJoker(drawResult.getJoker())) {


            DrawResult dr = drawResultRepository.findOne(drawResult.getDrawNum());
            if (dr == null) {
                drawResultRepository.save(drawResult);
                return "success";
            }
        }
        return "failed";
    }

    public String getLastDraw(){
        //DrawResult drawResultNum = drawResultRepository.findAllByOrderByDrawNumDesc().get(0);
        //String drawResult = drawResultRepository.findLastDrawResult(new PageRequest(0, 1, Sort.Direction.DESC, "drawNum")).iterator().next().getDrawNum().toString();
        return drawResultRepository.findLastDrawNum(new PageRequest(0, 1, Sort.Direction.DESC, "drawNum")).iterator().next().toString();
    }

    public List<DrawResult> getLatestDraws(int n){
        return drawResultRepository.findLastDrawResult(new PageRequest(0, n, Sort.Direction.DESC, "drawNum")).getContent();
    }

    public DrawResult getDrawFromDate(Date date){
        return drawResultRepository.findByDrawDate(date);
    }

    public List<JNumber> getByLeastRecent(boolean joker){
        if(joker){
            return transformData(drawResultRepository.findJokersByLeastRecent());
        }
        return transformData(drawResultRepository.findNumbersByLeastRecent());
    }

    public List<JNumber> getByLeastFrequent(boolean joker){
        if(joker){
            return transformData(drawResultRepository.findJokersByLeastFrequent());
        }
        return transformData(drawResultRepository.findNumbersByLeastFrequent());
    }

    private List<JNumber> transformData(List<Object> l){
        List<JNumber> finalList = new ArrayList<>(l.size());
        for (Object o : l){
            Object[] arr = (Object[]) o;
            finalList.add(new JNumber((Integer)arr[0], ((BigInteger)arr[1]).intValue(), (Date)arr[2]));
        }
        return finalList;
    }

    private boolean validateJoker(Integer joker) {
        return !(joker < 1 || joker > 20);
    }

    private boolean validateNumber(Integer number) {
        return !(number < 1 || number > 45);
    }

    public List<Combination> getCombinations(int N) {

        List<DrawResult> results = drawResultRepository.findAllByOrderByDrawNumDesc();

        Map<String, Combination> combOfN = combinations.getCopyOfN(N);


        for (DrawResult result : results){

            List<String> combOfSet = combinations.getListCombinations(new Integer[]{result.getN1(), result.getN2(),
                    result.getN3(), result.getN4(), result.getN5()}, N);
            for(String set : combOfSet){
                Combination c = combOfN.get(set);
                combOfN.put(set, new Combination(set, c.getFrequency()+1,
                        (c.getLastDrawDate() == null || c.getLastDrawDate().getTime() < result.getDrawDate().getTime())
                                ? result.getDrawDate() : c.getLastDrawDate()));
            }
        }

        List<Combination> finalList = new ArrayList<>(0);

        for (Map.Entry<String, Combination> entry : combOfN.entrySet()){
            if(entry.getValue().getFrequency() > 1){
                finalList.add(new Combination(entry.getKey(), entry.getValue().getFrequency(), entry.getValue().getLastDrawDate()));
            }
        }
        Collections.sort(finalList, Combination.COMPARE_BY_FREQ_DATE_DESC);

        return finalList;
    }

    public List<Combination> getCombinationsForSequence(String sequence, int N) {

        List<Combination> finalList = new ArrayList<>(0);

        String[] seq = sequence.split(",");
        List<Integer> list = new ArrayList<>(seq.length);
        for (String aSeq : seq) {
            list.add(Integer.parseInt(aSeq));
        }

        List<DrawResult> results = drawResultRepository.findAllByOrderByDrawNumDesc();

        Map<String, Combination> combOfN = combinations.getCombinationsForSequence(list.toArray(new Integer[list.size()]), N);

        for (DrawResult result : results){

            List<String> combOfSet = combinations.getListCombinations(new Integer[]{result.getN1(), result.getN2(),
                    result.getN3(), result.getN4(), result.getN5()}, N);
            for(String set : combOfSet){
                Combination c = combOfN.get(set);
                if(c!= null) {
                    combOfN.put(set, new Combination(set, c.getFrequency() + 1,
                            (c.getLastDrawDate() == null || c.getLastDrawDate().getTime() < result.getDrawDate().getTime())
                                    ? result.getDrawDate() : c.getLastDrawDate()));
                }
            }
        }


        for (Map.Entry<String, Combination> entry : combOfN.entrySet()){
            if(entry.getValue().getFrequency() > 0){
                finalList.add(new Combination(entry.getKey(), entry.getValue().getFrequency(), entry.getValue().getLastDrawDate()));
            }
        }
        Collections.sort(finalList, Combination.COMPARE_BY_FREQ_DATE_DESC);

        return finalList;
    }

    public List<Integer> getSuggestedCoupon(List<JNumber> r1, List<JNumber> f1, List<JNumber> r2, List<JNumber> f2, Integer limitR, Integer limitF) {
        List<Integer> suggested = new ArrayList<>(6);

        for(int i=0; i< limitF; i++){
            if(suggested.size() == 5){
                break;
            }
            int freqNumber = f1.get(i).getNumber();
            for(int j=0; j< limitR; j++){
                if(r1.get(j).getNumber() == freqNumber){
                    suggested.add(freqNumber);
                    break;
                }
            }
        }

        if(suggested.size() < 5){
            for(int i=0; i< limitR; i++){
                if(suggested.size() == 5){
                    break;
                }
                int number = r1.get(i).getNumber();
                if(!suggested.contains(number)){
                    suggested.add(number);
                }
            }
            while (suggested.size() < 5){
                suggested.add(-1);
            }
        }

        Collections.sort(suggested);

        for(int i=0; i < 20; i++){
            if(suggested.size() == 6){
                break;
            }
            int number = f2.get(i).getNumber();
            for(int j=0; j < 20; j++){
                if(r2.get(j).getNumber() == number){
                    suggested.add(number);
                    break;
                }
            }
        }

        return suggested;
    }
}