package examination.Controllers;

import examination.DataLayer.models.Answer;
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
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


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
            ModelAndView modelAndView = createRedirectModelAndView("/finish_exam.html");
            return modelAndView;
        }
    }

    protected ModelAndView createRedirectModelAndView(String url) {
        return new ModelAndView(new RedirectView(url));
    }

    @RequestMapping(value = {"/finish_exam.html"}, method = RequestMethod.GET)
    public ModelAndView finishExam() {
        //examinationService.finish();
        ModelAndView modelAndView = new ModelAndView("finish_exam");
        return modelAndView;
    }

    @RequestMapping(value = {"/submit_answer-json.json"})
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


    @RequestMapping(value = {"/repair.json"}, method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView showExamsList(@RequestParam(value = "studentId",
            required = true) long studentId) {
        List<Exam> examList = examinationService.getCurrentExams(studentId);
        if (examList == null) {
            return new ModelAndView("start");
        }
        List<Exam> incompleteExams = new ArrayList<Exam>();
        for (Exam exam : examList) {
            if (exam.getTimeFinish() == null) {
                incompleteExams.add(exam);
            }
        }
        if (examList.size() == 0) {
            return new ModelAndView("start");
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
