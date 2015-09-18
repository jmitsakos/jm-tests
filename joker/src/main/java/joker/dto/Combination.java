package joker.dto;

import java.util.Comparator;
import java.util.Date;

/**
 * Created by imitsakos on 4/9/2015.
 */
public class Combination {

    private String combination;
    private int frequency = 0;
    private Date lastDrawDate;

    public final static Comparator<Combination> COMPARE_BY_FREQ_DATE_DESC = new Comparator<Combination>() {
        @Override
        public int compare(final Combination c1, final Combination c2) {
            if(c2.getFrequency() == c1.getFrequency())
                return c2.lastDrawDate.compareTo(c1.lastDrawDate);
            else
                return c2.getFrequency() - c1.getFrequency();
        }
    };

    public Combination() {
    }

    public Combination(String combination, int frequency, Date lastDrawDate) {
        this.combination = combination;
        this.frequency = frequency;
        this.lastDrawDate = lastDrawDate;
    }


    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public Date getLastDrawDate() {
        return lastDrawDate;
    }

    public void setLastDrawDate(Date lastDrawDate) {
        this.lastDrawDate = lastDrawDate;
    }

    public String getCombination() {
        return combination;
    }

    public void setCombination(String combination) {
        this.combination = combination;
    }
}
