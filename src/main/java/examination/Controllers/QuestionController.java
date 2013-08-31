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
    @Autowired
    private ExaminationService examinationService;

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

    @RequestMapping(value = {"/start.html"}, method = RequestMethod.GET)
    public ModelAndView startExam(@RequestParam(value = "course", required = true) long courseId) {
        ModelAndView modelAndView = new ModelAndView("start");
        modelAndView.addObject("question_info", examinationService.start(2, courseId));
        return modelAndView;
    }

    @RequestMapping(value = {"/exam_question.html"}, method = RequestMethod.GET)
    public ModelAndView nextQuestion(@RequestParam(value = "exam_id",
            required = true) long examId) {
        ModelAndView modelAndView = new ModelAndView("nextQuestion");
        modelAndView.addObject("question_info", examinationService.next(examId));
        return modelAndView;
    }

    @RequestMapping(value = {"/submit_answer.html"}, method = RequestMethod.POST)
    public RedirectView submitAnswerPage(Answer answer) {
        /* save answer somewhere */
        RedirectView redirectView = new RedirectView("/exam_question.html?exam_id=" + answer.getExamId());
        return redirectView;
    }


}
