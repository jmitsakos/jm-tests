package joker.service;

import joker.domain.DrawResult;
import joker.repository.DrawResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by imitsakos on 23/7/2015.
 */
@Service
public class DrawResultService {

    @Autowired
    private DrawResultRepository drawResultRepository;


    public String getLastDraw(){
        return drawResultRepository.findLastDrawNum(new PageRequest(0, 1, Sort.Direction.DESC, "drawNum")).iterator().next().toString();
    }

    public List<DrawResult> getLatestDraws(int n){
        return drawResultRepository.findLastDrawResult(new PageRequest(0, n, Sort.Direction.DESC, "drawNum")).getContent();
    }

    public DrawResult getDrawFromDate(Date date){
        return drawResultRepository.findByDrawDate(date);
    }

}