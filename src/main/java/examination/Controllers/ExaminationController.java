package examination.Controllers;

import examination.DataLayer.models.Answer;
import examination.QuestionService.AnswerService;

import examination.QuestionService.ExaminationService;
import examination.QuestionService.models.QuestionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;


@Controller
public class ExaminationController {

    @Autowired
    private ExaminationService examinationService;
    @Autowired
    private AnswerService answerService;

    @RequestMapping(value = {"/start.html"}, method = RequestMethod.GET)
    public ModelAndView startExam() {
        ModelAndView modelAndView = new ModelAndView("start");
        return modelAndView;
    }

    @RequestMapping(value = {"/start.html"}, method = RequestMethod.POST)
    public ModelAndView startExam(@RequestParam(value = "courseId", required = true) long courseId,
                                  @RequestParam(value = "studentId", required = true) long studentId) {
        ModelAndView modelAndView = new ModelAndView("next_question");
        modelAndView.addObject("question_info", examinationService.start(studentId, courseId));
        return modelAndView;
    }

    @RequestMapping(value = {"/submit_answer.html"}, method = RequestMethod.POST)
    public RedirectView submitAnswer(@Valid Answer answer, BindingResult result) {
        if (result.hasErrors()) {
            return new RedirectView("/next_question.html");
        }
        answerService.manualSave(answer);
        long examId = answer.getExamId();
        QuestionInfo questionInfo = examinationService.next(examId);
        if (questionInfo.getQuestion() != null) {
            //        RedirectView redirectView = new RedirectView("/next_question.html", questionInfo);
            RedirectView redirectView = new RedirectView(createRedirectedUrl(examId));
            return redirectView;
        } else {
            return new RedirectView("/finish_exam.html");
        }
    }

    private String createRedirectedUrl(long examId) {
        return "/next_question.html?examid=" + examId;
    }

    @RequestMapping(value = {"/next_question.html"}, method = RequestMethod.GET)
    public ModelAndView nextQuestion(@RequestParam(value = "examid", required = true)
                                     long examId) {
        ModelAndView modelAndView = new ModelAndView("next_question");
        QuestionInfo questionInfo = examinationService.next(examId);
        modelAndView.addObject("question_info", questionInfo);
        return modelAndView;
    }

    @RequestMapping(value = {"/finish_exam.html"}, method = RequestMethod.GET)
    public ModelAndView finishExam() {
        //examinationService.finish();
        ModelAndView modelAndView = new ModelAndView("finish_exam");
        return modelAndView;
    }

    @RequestMapping(value = {"/submit_answer-json.json"})
    @ResponseBody
    public boolean saveAnswer(Answer answer) {
        return answerService.autoSave(answer);
    }
}
