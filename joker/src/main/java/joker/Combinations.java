package joker;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

import java.util.*;

/**
 * Created by imitsakos on 30/6/2015.
 */
public class Combinations {

    public Map<String, Integer> getCombinations1(Integer[] elements, int K){

        Map<String, Integer> combinations = new HashMap<>(0);

        int N = elements.length;

        if(K > N){
            System.out.println("Invalid input, K > N");
            return null;
        }
        // calculate the possible combinations
        // e.g. c(4,2)

        // get the combination by index
        // e.g. 01 --> AB , 23 --> CD
        int combination[] = new int[K];

        // position of current index
        //  if (r = 1)              r*
        //  index ==>        0   |   1   |   2
        //  element ==>      A   |   B   |   C
        int r = 0;
        int index = 0;

        while(r >= 0){
            // possible indexes for 1st position "r=0" are "0,1,2" --> "A,B,C"
            // possible indexes for 2nd position "r=1" are "1,2,3" --> "B,C,D"

            // for r = 0 ==> index < (4+ (0 - 2)) = 2
            if(index <= (N + (r - K))){
                combination[r] = index;

                // if we are at the last position print and increase the index
                if(r == K-1){

                    //do something with the combination e.g. add to list or print
                    //System.out.println(elements[combination[0]]+","+elements[combination[1]]+","+elements[combination[2]]);
                    combinations.put(elements[combination[0]].toString()+elements[combination[1]]+elements[combination[2]], 0);
                    index++;
                }
                else{
                    // select index for next position
                    index = combination[r]+1;
                    r++;
                }
            }
            else{
                r--;
                if(r > 0)
                    index = combination[r]+1;
                else
                    index = combination[0]+1;
            }
        }

        return combinations;
    }

    public Map<String, Integer> getCombinations(Integer[] elements, int k){
        ICombinatoricsVector<Integer> initialVector = Factory.createVector(elements);

        Generator<Integer> gen = Factory.createSimpleCombinationGenerator(initialVector, k);

        Map<String, Integer> combinations = new HashMap<>(0);

        /*List<CombinatoricsVector> list = ((SimpleCombinationGenerator) gen).generateAllObjects();

        for (CombinatoricsVector c : list){
            combinations.put(c.getValue(0).toString()+c.getValue(1)+c.getValue(2), 1);
        }*/

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
            combinations.put(sb.toString().replaceFirst(",",""), 0);
        }

        return combinations;
    }


    public List<String> getCombinations(List<Integer> elements, int k){
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

    /*public static void main(String[] args){
        Integer[] elements = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,
                21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45 };

        Combinations c = new Combinations();

        System.out.println("Combination 45 to 3: " + c.getCombinations1(elements, 3).size());

        System.out.println("Combination 45 to 3: " + c.getCombinations(elements, 3).size());

        //Collection<List<Integer>> c = Collections2.permutations(Arrays.asList(elements));
    }*/
}