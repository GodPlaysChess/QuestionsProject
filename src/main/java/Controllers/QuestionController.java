package Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class QuestionController {

    private QuestionService questrionService;

    @RequestMapping(value = {"/question.html"}, method = RequestMethod.GET)
    public ModelAndView showPage

    public QuestionController(QuestionService questrionService) {
        this.questrionService = questrionService;
    }
}
