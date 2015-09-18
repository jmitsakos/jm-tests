package joker.init;

import joker.service.DrawResultService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by imitsakos on 15/9/2015.
 */
public class Statistics {

    @Autowired
    private DrawResultService drawResultService;

    public String getLastDraw(){
        return drawResultService.getLastDraw();
    }
}
