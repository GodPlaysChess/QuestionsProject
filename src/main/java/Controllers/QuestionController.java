package Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class QuestionController {

    @RequestMapping(value = {"/question.html"}, method = RequestMethod.GET)
    public ModelAndView getNextPage() {
        System.out.println("nextpage");
        return new ModelAndView("Questions");
    }

}
