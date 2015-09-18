package joker;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by imitsakos on 25/6/2015.
 */
public class JokerNumber {

    private int number;
    private int fiveSetFrequency;
    private int jokerFrequency;
    private List<Date> fDates = new ArrayList<>(0);
    private List<Date> jDates = new ArrayList<>(0);


    public final static Comparator<JokerNumber> COMPARE_BY_FIVESET_FREQUENCY_DESC = new Comparator<JokerNumber>() {
        @Override
        public int compare(final JokerNumber jn1, final JokerNumber jn2) {
            return jn2.fiveSetFrequency - jn1.fiveSetFrequency;
        }
    };

    public final static Comparator<JokerNumber> COMPARE_BY_JOKER_FREQUENCY_DESC = new Comparator<JokerNumber>() {
        @Override
        public int compare(final JokerNumber jn1, final JokerNumber jn2) {
            return jn2.jokerFrequency - jn1.jokerFrequency;
        }
    };

    public final static Comparator<JokerNumber> COMPARE_BY_FIVESET_FREQUENCY_ASC = new Comparator<JokerNumber>() {
        @Override
        public int compare(final JokerNumber jn1, final JokerNumber jn2) {
            if(jn1.fiveSetFrequency != jn2.fiveSetFrequency) {
                return jn1.fiveSetFrequency - jn2.fiveSetFrequency;
            } else{
                if(CollectionUtils.isEmpty(jn1.getfDates())){
                    return jn1.getNumber()-jn2.getNumber();
                }
                return jn1.getfDates().get(jn1.getfDates().size()-1).compareTo(jn2.getfDates().get(jn2.getfDates().size()-1));
            }
        }
    };

    public final static Comparator<JokerNumber> COMPARE_BY_JOKER_FREQUENCY_ASC = new Comparator<JokerNumber>() {
        @Override
        public int compare(final JokerNumber jn1, final JokerNumber jn2) {
            if(jn1.jokerFrequency != jn2.jokerFrequency) {
                return jn1.jokerFrequency - jn2.jokerFrequency;
            } else {
                if(CollectionUtils.isEmpty(jn1.getjDates())){
                    return jn1.getNumber()-jn2.getNumber();
                }
                return jn1.getjDates().get(jn1.getjDates().size()-1).compareTo(jn2.getjDates().get(jn2.getjDates().size()-1));
            }
        }
    };


    public JokerNumber(int number, int fiveSetFrequency, int jokerFrequency) {
        this.number = number;
        this.fiveSetFrequency = fiveSetFrequency;
        this.jokerFrequency = jokerFrequency;
    }

    public int getFiveSetFrequency() {
        return fiveSetFrequency;
    }

    public void setFiveSetFrequency(int fiveSetFrequency) {
        this.fiveSetFrequency = fiveSetFrequency;
    }

    public int getJokerFrequency() {
        return jokerFrequency;
    }

    public void setJokerFrequency(int jokerFrequency) {
        this.jokerFrequency = jokerFrequency;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void addJokerFrequency(){
        jokerFrequency++;
    }

    public void addFiveSetFrequency(){
        fiveSetFrequency++;
    }

    public void addFDate(Date date){
        fDates.add(date);
    }

    public void addJDate(Date date){
        jDates.add(date);
    }

    public List<Date> getfDates() {
        return fDates;
    }

    public List<Date> getjDates() {
        return jDates;
    }
}
