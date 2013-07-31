package examination.Controllers;

import examination.DataLayer.models.Profile;
import examination.DataLayer.models.Question;
import examination.QuestionService.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
/*    @Autowired
    private AccountService accountService;*/

    @RequestMapping(value = {"/question.html"}, method = RequestMethod.GET)
    public ModelAndView getQuestionPage(@RequestParam(value = "questionid",
            required = true) long questionId) {
        ModelAndView modelAndView = new ModelAndView("question");
        modelAndView.addObject("getQuestionPage", questionService.getQuestion(questionId));
        return modelAndView;
    }

    @RequestMapping(value = {"/savequestion.html"}, method = RequestMethod.POST)
    public RedirectView saveQuestionPage(Question question) {
        questionService.addQuestion(question);
        RedirectView redirectView = new RedirectView("/mainpage.html");
        return redirectView;
    }

   @RequestMapping(value = {"/mainpage.html"}, method = RequestMethod.GET)
   public ModelAndView indexPage(){
       ModelAndView modelAndView = new ModelAndView("mainpage");
       return modelAndView;
   }

    @RequestMapping(value = {"/enter.html"}, method = RequestMethod.GET)
    public ModelAndView enterPage(){
        return new ModelAndView("enter");
    }

    @RequestMapping(value = {"/savename.html"}, method = RequestMethod.POST)
    public RedirectView saveName(@RequestParam(value = "lastName", required = false) String name) {
        //accountService.addProfile(profile);
        RedirectView redirectView = new RedirectView("/mainpage.html");
        return redirectView;
    }

    @RequestMapping(value = {"/questionlist.html"}, method = RequestMethod.GET)
    public ModelAndView questionList(){
        ModelAndView modelAndView = new ModelAndView("questionlist");
        modelAndView.addObject("questionList", questionService.listQuestions());
        return modelAndView;
    }


}
