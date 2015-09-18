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
            return drawResultRepository.findJokersByLeastRecent();
        }
        return drawResultRepository.findNumbersByLeastRecent();
    }

    public List<JNumber> getByLeastFrequent(boolean joker){
        if(joker){
            return drawResultRepository.findJokersByLeastFrequent();
        }
        return drawResultRepository.findNumbersByLeastFrequent();
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
}