package xls.reader;

import joker.JokerNumber;
import org.junit.Test;

/**
 * Created by imitsakos on 14/9/2015.
 */
public class UnitTest {

    @Test
    public void test1(){
        JokerNumber jn1 = new JokerNumber(1, 2, 3);
        JokerNumber jn2 = new JokerNumber(1, 2, 3);
        String a = new String("test");
        String b = new String("test");

        if(jn1 == jn2){
            System.out.println("true");
        } else {
            System.out.println("false");
        }
        if(jn1.equals(jn2)){
            System.out.println("true");
        } else {
            System.out.println("false");
        }
        if(a.equals(b)){
            System.out.println("true");
        } else {
            System.out.println("false");
        }
        if(a==b){
            System.out.println("true");
        } else {
            System.out.println("false");
        }
        System.out.println("res = " + (4 | 3));

        final long l = 4;
        Testa testa = new Testa();
        testa.setX(l);

        set(l);

        System.out.println("outside set long: " + l);

        set(testa);

        System.out.println("res = " + l);
        System.out.println("res = " + testa.getX());
        set1(testa);
        System.out.println("res = " + testa.getX());
    }


    public void set(long x){
        x = x +1;
        System.out.println("inside set long: "+ x);
    }

    public void set(Testa a){
        a.setX(a.getX() + 1);
    }

    //by value example
    public void set1(Testa a){
        a = new Testa();
        a.setX(a.getX() + 1);

    }

    private class Testa {
        long x;

        public long getX() {
            return x;
        }

        public void setX(long x) {
            this.x = x;
        }
    }
}
