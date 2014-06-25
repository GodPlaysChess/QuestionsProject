package examination.Controllers;

import examination.DataLayer.models.Answer;
import examination.DataLayer.models.Exam;
import examination.DataLayer.models.Question;
import examination.DataLayer.models.enums.Mark;
import examination.QuestionService.AnswerService;
import examination.QuestionService.ExaminationService;
import examination.QuestionService.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class EvaluationController {

    @Autowired
    private AnswerService answerService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ExaminationService examinationService;


    @RequestMapping(value = ("/exams_to_evaluate.html"), method = RequestMethod.GET)
    public ModelAndView toEvaluationPage(@RequestParam(value = "course_id") long courseId) {
        ModelAndView modelAndView = new ModelAndView("exams_evaluate");

        List<Exam> exams = examinationService.getInevaluatedExams();

        List<Exam> examsByCourse = new ArrayList<Exam>();
        for (Exam e : exams) {
            if (e.getCourseId() == courseId) {
                examsByCourse.add(e);
            }
        }
        if (examsByCourse.size() == 0) {
            modelAndView.addObject("exams", exams);
        } else {
            modelAndView.addObject("exams", examsByCourse);
        }

        return modelAndView;
    }

    @RequestMapping(value = {"/evaluate.html"}, method = RequestMethod.GET)
    public ModelAndView beginEvaluation(@RequestParam(value = "exam_id",
            required = true) long examId) {
        ModelAndView modelAndView = new ModelAndView("answers_list");
        List<Answer> answers = answerService.getAnswersByExamId(examId);
        List<Long> questions = new ArrayList<Long>();
        for (Answer a : answers) {
            questions.add(a.getQuestionId());
        }
        List<Question> questionList = questionService.selectList(questions);
        Map<Answer, Question> answerMap = new TreeMap<Answer, Question>(new Comparator<Answer>() {
            @Override
            public int compare(Answer o1, Answer o2) {
                return o1.getTimeStart().compareTo(o2.getTimeStart());
            }
        });
        for (int i = 0; i < questionList.size(); i++) {
            answerMap.put(answers.get(i), questionList.get(i));
        }
        modelAndView.addObject("aMap", answerMap);
        return modelAndView;
    }

    @RequestMapping(value = {"/evaluate.json"}, method = RequestMethod.POST)
    @ResponseBody
    public boolean evaluateAnswer(@RequestParam(value = "mark", required = true) Mark mark,
                                  @RequestParam(value = "answer_id", required = true) long answerID) {
        Answer answer = answerService.selectById(answerID);
        answer.setMark(mark);
        return answerService.update(answer);
    }

}
