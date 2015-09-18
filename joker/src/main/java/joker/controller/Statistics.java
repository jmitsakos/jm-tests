package joker.controller;

import joker.service.DrawResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("")
    public String init(Model model){
        model.addAttribute("infive", drawResultService.getByLeastRecent(false));
        model.addAttribute("joker", drawResultService.getByLeastRecent(true));
        model.addAttribute("infivef", drawResultService.getByLeastFrequent(false));
        model.addAttribute("jokerf", drawResultService.getByLeastFrequent(true));
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
