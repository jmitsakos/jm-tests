package joker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by imitsakos on 25/6/2015.
 */
public class Result {

    private long drawNum;
    private Date date;
    private List<Integer> numbers = new ArrayList<>(0);
    private int joker;

    public final static Comparator<Result> COMPARE_BY_DATE = new Comparator<Result>() {
        @Override
        public int compare(final Result r1, final Result r2) {
            return r1.date.compareTo(r2.date);
        }
    };

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void addNumber(int number){
        this.numbers.add(number);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public int getJoker() {
        return joker;
    }

    public void setJoker(int joker) {
        this.joker = joker;
    }

    public long getDrawNum() {
        return drawNum;
    }

    public void setDrawNum(long drawNum) {
        this.drawNum = drawNum;
    }
}

