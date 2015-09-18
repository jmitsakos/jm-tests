package joker.domain;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by imitsakos on 23/7/2015.
 */
@Entity
@Table(name="draw_result")
@Component
public class DrawResult implements Serializable {

    @Id
    @Column(name = "draw_num")
    private Long drawNum;

    @Column(nullable = false)
    private Integer n1;

    @Column(nullable = false)
    private Integer n2;

    @Column(nullable = false)
    private Integer n3;

    @Column(nullable = false)
    private Integer n4;

    @Column(nullable = false)
    private Integer n5;

    @Column(nullable = false)
    private Integer joker;

    @Column(name = "draw_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date drawDate;

    public DrawResult() {
        super();
    }

    public DrawResult(Long drawNum, Integer n1, Integer n2, Integer n3, Integer n4, Integer n5, Integer joker, Date drawDate) {
        super();
        this.drawNum = drawNum;
        this.n1 = n1;
        this.n2 = n2;
        this.n3 = n3;
        this.n4 = n4;
        this.n5 = n5;
        this.joker = joker;
        this.drawDate = drawDate;
    }

    public Long getDrawNum() {
        return drawNum;
    }

    public Integer getN1() {
        return n1;
    }

    public Integer getN2() {
        return n2;
    }

    public Integer getN3() {
        return n3;
    }

    public Integer getN4() {
        return n4;
    }

    public Integer getN5() {
        return n5;
    }

    public Integer getJoker() {
        return joker;
    }

    public Date getDrawDate() {
        return drawDate;
    }


}
