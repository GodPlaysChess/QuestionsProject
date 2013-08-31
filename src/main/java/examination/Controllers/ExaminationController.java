package examination.Controllers;

import examination.DataLayer.models.Answer;
import examination.QuestionService.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class ExaminationController {

    @Autowired
    private ExaminationService examinationService;

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
