package joker.controller;

import joker.JokerNumber;
import joker.Statistics;
import joker.service.DrawResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * User: imitsakos
 * Date: 9/7/2014
 * Time: 11:12 πμ
 */

@Controller
public class Home {

    private Statistics statistics;

    @Autowired
    private DrawResultService drawResultService;

    @RequestMapping("/")
    public String welcome(Map<String, Object> model){
        model.put("latest", drawResultService.getLatestDraws(5));
        return "welcome";
    }

    @RequestMapping("/{date}")
    public String byDate(Map<String, Object> model, @PathVariable String date) throws ParseException {
        model.put("bydate", drawResultService.getDrawFromDate(new SimpleDateFormat("yyyy-MM-dd").parse(date)));
        return "welcome";
    }

    @RequestMapping("/getnumbers")
    public ModelAndView home() {

        int nLimit = 5;
        int jLimit = 2;
        /*if(files.length == 2){
            nLimit = 15;
            jLimit = 6;
        }*/

        List<JokerNumber> numbers =  statistics.getResults(new String[]{"resultsAll.txt"});

        Collections.sort(numbers, JokerNumber.COMPARE_BY_FIVESET_FREQUENCY_DESC);

        int maxInFiveFreq = numbers.get(0).getFiveSetFrequency();

        /*for(JokerNumber jokerNumber : numbers){
            if(jokerNumber.getFiveSetFrequency() > 0) {
                System.out.println("Number " + jokerNumber.getNumber() + " has appeared " + jokerNumber.getFiveSetFrequency() + " in five set");
            }
        }*/

        List<String> sfn = new ArrayList<>(0);
        Collections.sort(numbers, JokerNumber.COMPARE_BY_FIVESET_FREQUENCY_ASC);

        for(JokerNumber jokerNumber : numbers){
            //if(jokerNumber.getFiveSetFrequency() < nLimit || jokerNumber.getFiveSetFrequency() > (maxInFiveFreq - nLimit)) {
                sfn.add(jokerNumber.getNumber() + "," + jokerNumber.getFiveSetFrequency()
                        + "," + (CollectionUtils.isEmpty(jokerNumber.getfDates()) ? null
                        : new SimpleDateFormat("dd/MM/yyyy").format(jokerNumber.getfDates().get(jokerNumber.getfDates().size()-1))));
           // }
        }


        Collections.sort(numbers, JokerNumber.COMPARE_BY_JOKER_FREQUENCY_DESC);

        int maxJokerFreq = numbers.get(0).getJokerFrequency();

        /*for(JokerNumber jokerNumber : numbers){
            if(jokerNumber.getNumber() < 21) {
                System.out.println("Number " + jokerNumber.getNumber() + " has appeared " + jokerNumber.getJokerFrequency() + " as a joker");
            }
        }*/


        Collections.sort(numbers, JokerNumber.COMPARE_BY_JOKER_FREQUENCY_ASC);

        List<String> sjn = new ArrayList<>(0);

        for(JokerNumber jokerNumber : numbers){
            //if(jokerNumber.getNumber() < 21 && (jokerNumber.getJokerFrequency() < jLimit || jokerNumber.getJokerFrequency() >= (maxJokerFreq - jLimit))) {
            if(jokerNumber.getNumber() < 21){
                sjn.add(jokerNumber.getNumber() + "," + jokerNumber.getJokerFrequency()
                        + "," + (CollectionUtils.isEmpty(jokerNumber.getjDates()) ? null
                        : new SimpleDateFormat("dd/MM/yyyy").format(jokerNumber.getjDates().get(jokerNumber.getjDates().size()-1))));
            }
        }


        ModelMap   model = new ModelMap();
        model.addAttribute("infive", sfn);
        model.addAttribute("joker", sjn);
        //return "home";
        return new ModelAndView("home", model);
    }

    /*public static void main(String[] args) throws Exception {
        SpringApplication.run(Example.class, args);
    }*/
}
