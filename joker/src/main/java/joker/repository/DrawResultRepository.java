package joker.repository;

import joker.domain.DrawResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by imitsakos on 23/7/2015.
 */
public interface DrawResultRepository extends CrudRepository<DrawResult, Long> {

    DrawResult findByDrawDate(Date date);

    List<DrawResult> findAllByOrderByDrawNumDesc();

    @Query("SELECT dr FROM DrawResult dr")
    Page<DrawResult> findLastDrawResult(Pageable pageable);

    @Query("SELECT dr.drawNum FROM DrawResult dr")
    Page<Long> findLastDrawNum(Pageable pageable);

    @Query(value = "select a.n as number, count(a.n) as freq,  (select max(d.draw_date) from draw_result d where a.n in (d.n1, d.n2, d.n3, d.n4, d.n5) ) as lastOccurred from " +
            "(" +
            "select n1 as n from draw_result " +
            " UNION ALL " +
            "select n2 as n from draw_result" +
            " UNION ALL " +
            "select n3 as n from draw_result" +
            " UNION ALL " +
            "select n4 as n from draw_result" +
            " UNION ALL " +
            "select n5 as n from draw_result" +
            ") as a" +
            " group by a.n order by lastOccurred, freq", nativeQuery = true)
    List<Object> findNumbersByLeastRecent();

    @Query(value = "select a.n as number, count(a.n) as freq,  (select max(d.draw_date) from draw_result d where a.n in (d.n1, d.n2, d.n3, d.n4, d.n5) ) as lastOccurred from " +
            "(" +
            "select n1 as n from draw_result " +
            " UNION ALL " +
            "select n2 as n from draw_result" +
            " UNION ALL " +
            "select n3 as n from draw_result" +
            " UNION ALL " +
            "select n4 as n from draw_result" +
            " UNION ALL " +
            "select n5 as n from draw_result" +
            ") as a" +
            " group by a.n order by freq, lastOccurred", nativeQuery = true)
    List<Object> findNumbersByLeastFrequent();

    @Query(value = "select joker as number, count(joker) as freq, (select max(d.draw_date) from draw_result d where number = d.joker ) as lastOccurred" +
            " from draw_result " +            
            " group by number order by lastOccurred, freq", nativeQuery = true)
    List<Object> findJokersByLeastRecent();

    @Query(value = "select joker as number, count(joker) as freq, (select max(d.draw_date) from draw_result d where number = d.joker ) as lastOccurred" +
            " from draw_result " +
            " group by number order by freq, lastOccurred", nativeQuery = true)
    List<Object> findJokersByLeastFrequent();


}
