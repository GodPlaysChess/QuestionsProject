package examination.Controllers;

import examination.DataLayer.models.Answer;
import examination.DataLayer.models.Course;
import examination.DataLayer.models.Question;
import examination.QuestionService.ExaminationService;
import examination.QuestionService.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;


    @RequestMapping(value = {"/question.html"}, method = RequestMethod.GET)
    public ModelAndView getQuestionPage(@RequestParam(value = "questionid",
            required = true) long questionId) {
        ModelAndView modelAndView = new ModelAndView("question");
        modelAndView.addObject("getQuestionPage", questionService.getQuestion(questionId));
        return modelAndView;
    }

    @RequestMapping(value = {"/savequestion.html"}, method = RequestMethod.POST)
    public RedirectView saveQuestionPage(@Valid Question question, BindingResult result) {
        if (result.hasErrors()) {
            // TODO add info about bad fields
            return new RedirectView(createRedirectUrl(question.getId()));
        }

        questionService.addOrModifyQuestion(question);
        RedirectView redirectView = new RedirectView(createRedirectUrl(question.getId()));
        return redirectView;
    }

    private static String createRedirectUrl(long questionId) {
        return "/question.html?questionid=" + questionId;
    }

    @RequestMapping(value = {"/mainpage.html"}, method = RequestMethod.GET)
    public ModelAndView indexPage() {
        ModelAndView modelAndView = new ModelAndView("mainpage");
        return modelAndView;
    }

    @RequestMapping(value = {"/enter.html"}, method = RequestMethod.GET)
    public ModelAndView enterPage() {
        return new ModelAndView("enter");
    }

    @RequestMapping(value = {"/savename.html"}, method = RequestMethod.POST)
    public RedirectView saveName(@RequestParam(value = "lastName", required = false) String name) {
        RedirectView redirectView = new RedirectView("/mainpage.html");
        return redirectView;
    }

    @RequestMapping(value = {"/questionlist.html"}, method = RequestMethod.GET)
    public ModelAndView questionList() {
        ModelAndView modelAndView = new ModelAndView("questionlist");
        return modelAndView;
    }

    // here are examination service controllers




}
