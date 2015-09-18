package joker.dto;

import java.util.Date;

/**
 * Created by imitsakos on 4/9/2015.
 */
public class JNumber {

    private int number;
    private int frequency;
    private Date drawDate;

    public JNumber(int number, int frequency, Date drawDate) {
        this.number = number;
        this.frequency = frequency;
        this.drawDate = drawDate;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public Date getDrawDate() {
        return drawDate;
    }

    public void setDrawDate(Date drawDate) {
        this.drawDate = drawDate;
    }
}
