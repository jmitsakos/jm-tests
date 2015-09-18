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

}
