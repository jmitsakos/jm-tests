package joker.controller;

import joker.domain.DrawResult;
import joker.service.DrawResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: imitsakos
 * Date: 9/7/2014
 * Time: 11:12 πμ
 */

@Controller
@RequestMapping("/add")
public class AddResult {

    private static final Logger logger = LoggerFactory.getLogger(AddResult.class);

    @Autowired
    private DrawResultService drawResultService;

    @RequestMapping("")
    public String init(Model model){
        model.addAttribute("lastDrawNum", drawResultService.getLastDraw());
        return "add";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String newResult(@RequestParam @NotNull String drawNum,
                            @RequestParam @NotNull String n1,
                            @RequestParam @NotNull String n2,
                            @RequestParam @NotNull String n3,
                            @RequestParam @NotNull String n4,
                            @RequestParam @NotNull String n5,
                            @RequestParam @NotNull String joker,
                            @RequestParam @NotNull String drawDate,
                            Model model){

        Date dateInserted = null;

        try {
            dateInserted = new SimpleDateFormat("dd/MM/yyyy").parse(drawDate);


            DrawResult drawResult = new DrawResult(Long.parseLong(drawNum), Integer.parseInt(n1),
                    Integer.parseInt(n2), Integer.parseInt(n3), Integer.parseInt(n4), Integer.parseInt(n5),
                    Integer.parseInt(joker), dateInserted);

            String result = drawResultService.save(drawResult);

            model.addAttribute("message", result);
        } catch (ParseException e) {
            logger.error("Exeption occurred:", e);
            model.addAttribute("message", "exception occurred: " + e);
        }

        return "addResult";
    }


}
