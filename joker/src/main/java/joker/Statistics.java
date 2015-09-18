package joker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by imitsakos on 25/6/2015.
 */

public class Statistics {

    private static final Logger logger = LoggerFactory.getLogger(Statistics.class);


    private List<JokerNumber> numbers;

    Integer[] listNumbers = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,
            21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45 };

    public Statistics() {
        init();
    }

    public void init() {
        numbers = new ArrayList<>(0);
        numbers.add(new JokerNumber(1,0,0));
        numbers.add(new JokerNumber(2,0,0));
        numbers.add(new JokerNumber(3,0,0));
        numbers.add(new JokerNumber(4,0,0));
        numbers.add(new JokerNumber(5,0,0));
        numbers.add(new JokerNumber(6,0,0));
        numbers.add(new JokerNumber(7,0,0));
        numbers.add(new JokerNumber(8,0,0));
        numbers.add(new JokerNumber(9,0,0));
        numbers.add(new JokerNumber(10,0,0));
        numbers.add(new JokerNumber(11,0,0));
        numbers.add(new JokerNumber(12,0,0));
        numbers.add(new JokerNumber(13,0,0));
        numbers.add(new JokerNumber(14,0,0));
        numbers.add(new JokerNumber(15,0,0));
        numbers.add(new JokerNumber(16,0,0));
        numbers.add(new JokerNumber(17,0,0));
        numbers.add(new JokerNumber(18,0,0));
        numbers.add(new JokerNumber(19,0,0));
        numbers.add(new JokerNumber(20,0,0));
        numbers.add(new JokerNumber(21,0,0));
        numbers.add(new JokerNumber(22,0,0));
        numbers.add(new JokerNumber(23,0,0));
        numbers.add(new JokerNumber(24,0,0));
        numbers.add(new JokerNumber(25,0,0));
        numbers.add(new JokerNumber(26,0,0));
        numbers.add(new JokerNumber(27,0,0));
        numbers.add(new JokerNumber(28,0,0));
        numbers.add(new JokerNumber(29,0,0));
        numbers.add(new JokerNumber(30,0,0));
        numbers.add(new JokerNumber(31,0,0));
        numbers.add(new JokerNumber(32,0,0));
        numbers.add(new JokerNumber(33,0,0));
        numbers.add(new JokerNumber(34,0,0));
        numbers.add(new JokerNumber(35,0,0));
        numbers.add(new JokerNumber(36,0,0));
        numbers.add(new JokerNumber(37,0,0));
        numbers.add(new JokerNumber(38,0,0));
        numbers.add(new JokerNumber(39,0,0));
        numbers.add(new JokerNumber(40,0,0));
        numbers.add(new JokerNumber(41,0,0));
        numbers.add(new JokerNumber(42,0,0));
        numbers.add(new JokerNumber(43,0,0));
        numbers.add(new JokerNumber(44,0,0));
        numbers.add(new JokerNumber(45,0,0));
    }

    public void getFrequency(String[] files){

        List<Result> results = new ArrayList<>(0);

        int nLimit = 5;
        int jLimit = 2;
        if(files.length == 2){
           nLimit = 15;
           jLimit = 6;
        }

        for(String f : files) {
            results.addAll(parseFile(f));

        }
        if(!CollectionUtils.isEmpty(results)){
            for (Result result : results){
                int joker = result.getJoker();
                numbers.get(joker-1).addJokerFrequency();
                numbers.get(joker-1).addJDate(result.getDate());
                for(int resultNumber : result.getNumbers()){
                    numbers.get(resultNumber-1).addFiveSetFrequency();
                    numbers.get(resultNumber-1).addFDate(result.getDate());
                }
            }
        } else {
            System.exit(1);
        }

        Collections.sort(numbers, JokerNumber.COMPARE_BY_FIVESET_FREQUENCY_DESC);

        for(JokerNumber jokerNumber : numbers){
            if(jokerNumber.getFiveSetFrequency() > 0) {
                System.out.println("Number " + jokerNumber.getNumber() + " has appeared " + jokerNumber.getFiveSetFrequency() + " in five set");
                //+ jokerNumber.getJokerFrequency() + " as a joker");
            }
        }

        System.out.println("--------Suggested numbers in five set:");
        Collections.sort(numbers, JokerNumber.COMPARE_BY_FIVESET_FREQUENCY_ASC);

        for(JokerNumber jokerNumber : numbers){
            if(jokerNumber.getFiveSetFrequency() < nLimit) {
                System.out.println(jokerNumber.getNumber());
            }
        }



        System.out.println("-----------------------");

        Collections.sort(numbers, JokerNumber.COMPARE_BY_JOKER_FREQUENCY_DESC);

        for(JokerNumber jokerNumber : numbers){
            if(jokerNumber.getNumber() < 21) {
                System.out.println("Number " + jokerNumber.getNumber() + " has appeared " + jokerNumber.getJokerFrequency() + " as a joker");
            }
        }

        System.out.println("--------Suggested joker numbers:");
        Collections.sort(numbers, JokerNumber.COMPARE_BY_JOKER_FREQUENCY_ASC);

        for(JokerNumber jokerNumber : numbers){
            if(jokerNumber.getNumber() < 21 && jokerNumber.getJokerFrequency() < jLimit) {
                System.out.println(jokerNumber.getNumber());
            }
        }

    }


    public List<JokerNumber> getResults(String[] files){
        init();

        List<Result> results = new ArrayList<>(0);


        for(String f : files) {
            results.addAll(parseFile(f));

        }
        if(!CollectionUtils.isEmpty(results)){
            for (Result result : results){
                int joker = result.getJoker();
                numbers.get(joker-1).addJokerFrequency();
                numbers.get(joker-1).addJDate(result.getDate());
                for(int resultNumber : result.getNumbers()){
                    numbers.get(resultNumber-1).addFiveSetFrequency();
                    numbers.get(resultNumber-1).addFDate(result.getDate());
                }
            }
        }

       return numbers;
    }

    public void getCombinations(String filename, int N){

        List<Result> results = parseFile(filename);

        Combinations combinations = new Combinations();
        Map<String, Integer> combOfN = combinations.getCombinations(listNumbers, N);


        for (Result result : results){
            List<Integer> nums = result.getNumbers();

            List<String> combOfSet = combinations.getCombinations(nums, N);
            for(String set : combOfSet){
                combOfN.put(set, combOfN.get(set) + 1);
            }
        }

        Map<String, Integer> sortedMap = sortByComparator(combOfN);

        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()){
            if(entry.getValue() > 1){
                System.out.println(entry.getKey() + " times: " + entry.getValue());
            }
        }

    }


    public List<Result> parseFile(String filename) {

        List<Result> results = new ArrayList<>(0);
        try {
            File file = new File(Thread.currentThread().getContextClassLoader().getResource(filename).getFile());
            FileInputStream fis = new FileInputStream(file);

            DataInputStream in = new DataInputStream(fis);
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String strLine;


            while ((strLine = br.readLine()) != null) {

                String[] line = strLine.split(",");
                Result result = new Result();
                result.setJoker(Integer.parseInt(line[5]));
                result.addNumber(Integer.parseInt(line[0]));
                result.addNumber(Integer.parseInt(line[1]));
                result.addNumber(Integer.parseInt(line[2]));
                result.addNumber(Integer.parseInt(line[3]));
                result.addNumber(Integer.parseInt(line[4]));
                result.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(line[6]));

                results.add(result);

            }

            in.close();
        } catch (Exception e) {
            logger.error("Exception occurred: ", e);
        }

        return results;

    }

    /*public static void main(String[] args){
        Statistics statistics = new Statistics();
        //statistics.getFrequency(new String[] {"2015/results.txt"});
        //statistics.getFrequency(new String[] {"2015/results.txt", "2014/results.txt"});
        statistics.getCombinations("resultsAll.txt", 4);
    }*/

    private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap) {

        // Convert Map to List
        List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

        // Sort list with comparator, to compare the Map values
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // Convert sorted map back to a Map
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
}