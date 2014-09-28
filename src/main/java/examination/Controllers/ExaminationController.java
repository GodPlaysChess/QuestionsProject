package examination.Controllers;

import examination.DataLayer.models.Answer;
import examination.DataLayer.models.Course;
import examination.DataLayer.models.Exam;
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

import javax.validation.Valid;
import java.util.List;


@Controller
public class ExaminationController extends BaseController {

    @Autowired
    private ExaminationService examinationService;
    @Autowired
    private AnswerService answerService;

    @RequestMapping(value = {"/start.html"}, method = RequestMethod.GET)
    public ModelAndView startExam() {
        ModelAndView modelAndView = new ModelAndView("start");
        List<Course> courses = examinationService.getCoursesList(0, 0);
        modelAndView.addObject("courses", courses);
        modelAndView.addObject("showRepair", examinationService.getIncompleteExams(getCurrentUserId()).size()>0);
        return modelAndView;
    }

    @RequestMapping(value = {"/start.html"}, method = RequestMethod.POST)
    public ModelAndView startExam(@RequestParam(value = "course_id", required = true) long courseId) {
        ModelAndView modelAndView = new ModelAndView("next_question");
        long userId = getCurrentUserId();

        modelAndView.addObject("question_info", examinationService.start(userId, courseId));
        return modelAndView;
    }

    @RequestMapping(value = {"/submit_answer.html"}, method = RequestMethod.POST)
    public ModelAndView submitAnswer(@Valid Answer answer, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("next_question");
            long examId = answer.getExamId();
            QuestionInfo questionInfo = examinationService.current(examId);
            modelAndView.addObject("question_info", questionInfo);
            return modelAndView;
        }
        answerService.manualSave(answer);
        long examId = answer.getExamId();
        QuestionInfo questionInfo = examinationService.next(examId);
        if (questionInfo.getQuestion() != null) {
            ModelAndView modelAndView = new ModelAndView("next_question");
            modelAndView.addObject("question_info", questionInfo);
            return modelAndView;
        } else {
            return createRedirectModelAndView("/finish_exam.html");
        }
    }

    @RequestMapping(value = {"/finish_exam.html"}, method = RequestMethod.GET)
    public ModelAndView finishExam() {
        return new ModelAndView("finish_exam");
    }

    @RequestMapping(value = {"/submit_answer-json.json"}, method = RequestMethod.POST)
    @ResponseBody
    public boolean saveAnswer(@Valid Answer answer, BindingResult result) {
        if (result.hasErrors()) {
            return false;
        }
        return answerService.autoSave(answer);
    }

    @RequestMapping(value={"/repair.html"}, method=RequestMethod.POST)
    public ModelAndView continueExam(@RequestParam(value = "examId", required = true) long examId){
        QuestionInfo questionInfo = examinationService.current(examId);
        ModelAndView modelAndView = new ModelAndView("next_question");
        modelAndView.addObject("question_info", questionInfo);
        return modelAndView;
    }

    @RequestMapping(value = {"/repair.html"}, method = RequestMethod.GET)
    public ModelAndView showExamsList() {
        List<Exam> incompleteExams = examinationService.getIncompleteExams(getCurrentUserId());
        if (incompleteExams.size() == 0) {
            return createRedirectModelAndView("start.html");
        } else if (incompleteExams.size() == 1) {
            Exam exam = incompleteExams.get(0);
            QuestionInfo questionInfo = examinationService.current(exam.getId());
            ModelAndView modelAndView = new ModelAndView("next_question");
            modelAndView.addObject("question_info", questionInfo);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("exam_list");
            modelAndView.addObject("exam_list", incompleteExams);
            return modelAndView;
        }

    }
}

//jquery templates
