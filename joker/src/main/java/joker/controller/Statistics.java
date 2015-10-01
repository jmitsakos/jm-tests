package joker.controller;

import joker.dto.JNumber;
import joker.service.DrawResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * User: imitsakos
 * Date: 9/7/2014
 * Time: 11:12 πμ
 */

@Controller
@RequestMapping("/stats")
public class Statistics {

    private static final Logger logger = LoggerFactory.getLogger(Statistics.class);

    @Autowired
    private DrawResultService drawResultService;

    @RequestMapping("/{limitR}/{limitF}")
    public String init(Model model, @PathVariable Integer limitR, @PathVariable Integer limitF){
        List<JNumber> r1 = drawResultService.getByLeastRecent(false);
        model.addAttribute("infive", r1);
        List<JNumber> r2 = drawResultService.getByLeastRecent(true);
        model.addAttribute("joker", r2);
        List<JNumber> f1 = drawResultService.getByLeastFrequent(false);
        model.addAttribute("infivef", f1);
        List<JNumber> f2 = drawResultService.getByLeastFrequent(true);
        model.addAttribute("jokerf", f2);
        model.addAttribute("suggested", drawResultService.getSuggestedCoupon(r1, f1, r2, f2, limitR, limitF));
        return "statistics";
    }

    @RequestMapping("combinations/{number}")
    public String combinationsAll(Model model, @PathVariable Integer number){

        String key = "comb"+number;
        model.addAttribute(key, drawResultService.getCombinations(number));
        return "combinations";
    }

    @RequestMapping("combinations")
    public String combinations(Model model){

        model.addAttribute("comb3", drawResultService.getCombinations(3));
        model.addAttribute("comb4", drawResultService.getCombinations(4));
        model.addAttribute("comb5", drawResultService.getCombinations(5));
        return "combinations";
    }

    @RequestMapping("combinations/get/{sequence}/{number}")
    public String getCombinationForSequence(Model model, @PathVariable String sequence, @PathVariable Integer number){

        model.addAttribute("comb", drawResultService.getCombinationsForSequence(sequence, number));
        model.addAttribute("number", number);
        return "combinationsSeq";
    }
}
