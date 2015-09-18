package joker.util;

import joker.dto.Combination;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

import java.util.*;

/**
 * Created by imitsakos on 30/6/2015.
 */
public class Combinations {

    private static final Integer[] listNumbers = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,
            21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45 };

    private final Map<String, Combination> combOf3;
    private final Map<String, Combination> combOf4;
    private final Map<String, Combination> combOf5;


    public Combinations() {
        combOf3 = getCombinations(listNumbers, 3);
        combOf4 = getCombinations(listNumbers, 4);
        combOf5 = getCombinations(listNumbers, 5);
    }

    public List<String> getListCombinations(Integer[] elements, int k){
        ICombinatoricsVector<Integer> initialVector = Factory.createVector(elements);

        Generator<Integer> gen = Factory.createSimpleCombinationGenerator(initialVector, k);

        List<String> combinations = new ArrayList<>(0);

        for (ICombinatoricsVector<Integer> c : gen){
            List<Integer> temp = new ArrayList<>(0);
            for(int i = 0; i < k; i++){
                temp.add(c.getValue(i));
            }
            Collections.sort(temp);
            StringBuilder sb = new StringBuilder(0);
            for(int i = 0; i < k; i++){
                sb.append(",").append(temp.get(i));
            }
            combinations.add(sb.toString().replaceFirst(",",""));
        }

        return combinations;
    }

    private static Map<String, Combination> getCombinations(Integer[] elements, int k){
        ICombinatoricsVector<Integer> initialVector = Factory.createVector(elements);

        Generator<Integer> gen = Factory.createSimpleCombinationGenerator(initialVector, k);

        Map<String, Combination> combinations = new HashMap<>(0);

        for (ICombinatoricsVector<Integer> c : gen){
            List<Integer> temp = new ArrayList<>(0);
            for(int i = 0; i < k; i++){
                temp.add(c.getValue(i));
            }
            Collections.sort(temp);
            StringBuilder sb = new StringBuilder(0);
            for(int i = 0; i < k; i++){
                sb.append(",").append(temp.get(i));
            }
            combinations.put(sb.toString().replaceFirst(",", ""), new Combination());
        }

        return combinations;
    }

    public Map<String, Combination> getCopyOfN(int N) {

        int lenght = 0;
        if(N==3){
            lenght = combOf3.size();
        } else if(N==4){
            lenght = combOf4.size();
        } else {
            lenght = combOf5.size();
        }
        Map<String, Combination> combOfNFinal = new HashMap<>(lenght);

        if(N==3) {
            for (Map.Entry<String, Combination> entry : combOf3.entrySet()) {
                combOfNFinal.put(entry.getKey(), new Combination(entry.getKey(), entry.getValue().getFrequency(), entry.getValue().getLastDrawDate()));
            }
        } else if(N==4){
            for (Map.Entry<String, Combination> entry : combOf4.entrySet()) {
                combOfNFinal.put(entry.getKey(), new Combination(entry.getKey(), entry.getValue().getFrequency(), entry.getValue().getLastDrawDate()));
            }
        } else {
            for (Map.Entry<String, Combination> entry : combOf5.entrySet()) {
                combOfNFinal.put(entry.getKey(), new Combination(entry.getKey(), entry.getValue().getFrequency(), entry.getValue().getLastDrawDate()));
            }
        }

        return combOfNFinal;
    }

    public Map<String, Combination> getCombinationsForSequence(Integer[] elements, int k){
        ICombinatoricsVector<Integer> initialVector = Factory.createVector(elements);

        Generator<Integer> gen = Factory.createSimpleCombinationGenerator(initialVector, k);

        Map<String, Combination> combinations = new HashMap<>(0);

        for (ICombinatoricsVector<Integer> c : gen){
            List<Integer> temp = new ArrayList<>(0);
            for(int i = 0; i < k; i++){
                temp.add(c.getValue(i));
            }
            Collections.sort(temp);
            StringBuilder sb = new StringBuilder(0);
            for(int i = 0; i < k; i++){
                sb.append(",").append(temp.get(i));
            }
            combinations.put(sb.toString().replaceFirst(",", ""), new Combination());
        }

        return combinations;
    }
}