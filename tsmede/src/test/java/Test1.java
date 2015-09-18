import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by imitsakos on 9/7/2015.
 */
public class Test1 {

    @Test
    public void test(){

        for(int i = 0; i < 10; i++ ) {
            System.out.println(returnSequence());
        }
        System.out.println("-------------------");
        for(int i = 0; i < 10; i++ ) {
            System.out.println(returnSequence1());
        }

    }

    public String returnSequence(){


        Random random = new Random();
        int mx = random.nextInt(2);
        int my = Math.abs(mx-1);

        int wx = random.nextInt(2);
        int wy = Math.abs(wx-1);

        int[] m = {mx, my};
        int[] w = {wx, wy};

        int ms = new Random().nextInt(2);
        int ws = new Random().nextInt(2);
        return m[ms] + ", " + w[ws];
    }

    public String returnSequence1(){

        List<String> lx  = new ArrayList<>(0);
        lx.add("H");
        lx.add("P");
        Collections.shuffle(lx);

        List<String> ly  = new ArrayList<>(0);
        ly.add("H");
        ly.add("P");
        Collections.shuffle(ly);

        int px = new Random().nextInt(2);
        int py = new Random().nextInt(2);



        return lx.get(px) + ", " + ly.get(py);
    }
}
