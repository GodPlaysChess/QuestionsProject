package examination.Controllers;

import examination.QuestionService.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = {"/question.html"}, method = RequestMethod.GET)
    public ModelAndView getQuestionPage(@RequestParam(value = "questionid", required = true) long questionId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("getQuestionPage", questionService.getQuestion(questionId));
        return modelAndView;
    }

}
