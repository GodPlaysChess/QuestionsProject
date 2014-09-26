package examination.Controllers;

import examination.DataLayer.models.Answer;
import examination.DataLayer.models.Exam;
import examination.DataLayer.models.Question;
import examination.DataLayer.models.enums.Mark;
import examination.QuestionService.AnswerService;
import examination.QuestionService.ExaminationService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class EvaluationController {

    @Autowired
    private AnswerService answerService;
    @Autowired
    private ExaminationService examinationService;


    public static class Pair {
        Answer key;
        Question value;

        private Pair(Answer key, Question value) {
            this.key = key;
            this.value = value;
        }

        public Answer getKey() {
            return key;
        }

        public void setKey(Answer key) {
            this.key = key;
        }

        public Question getValue() {
            return value;
        }

        public void setValue(Question value) {
            this.value = value;
        }
    }


    @RequestMapping(value = ("/exams_to_evaluate.html"), method = RequestMethod.GET)
    public ModelAndView toEvaluationPage(@RequestParam(value = "course_id") long courseId) {
        ModelAndView modelAndView = new ModelAndView("exams_evaluate");
        List<Exam> exams = examinationService.getInevaluatedExams(courseId);
        modelAndView.addObject("exams", exams);
        return modelAndView;
    }

    @RequestMapping(value = {"/evaluate.html"}, method = RequestMethod.GET)
    public ModelAndView beginEvaluation(@RequestParam(value = "exam_id",
            required = true) long examId) {
        ModelAndView modelAndView = new ModelAndView("answers_list");
        Exam exam = examinationService.selectById(examId);
        List<Answer> answers = answerService.getAnswersByExamId(examId);
        List<Question> questionList = exam.getQuestions();
        List<Pair> answerMap = new ArrayList<>(questionList.size());
        for (int i = 0; i < questionList.size(); i++) {
            Question question = questionList.get(i);
            Answer answer = getCurrentAnswer(answers, question, i, examId);
            answerMap.add(new Pair(answer, question));
        }
        modelAndView.addObject("aMap", answerMap);
        return modelAndView;
    }

    private Answer getCurrentAnswer(List<Answer> answers, final Question question, int i, long examId) {
        Predicate condition = new Predicate() {
            @Override
            public boolean evaluate(Object sample) {
                return ((Answer)sample).getQuestionId() == question.getId();
            }
        };
        Answer answer = (Answer) CollectionUtils.find(answers, condition);
        if (answer == null) {
            answer = new Answer();
            answer.setQuestionId(question.getId());
            answer.setExamId(examId);
            answer.setText("No answer");
            answerService.autoSave(answer);
        }
        return answer;
    }

    @RequestMapping(value = {"/evaluate.json"}, method = RequestMethod.POST)
    @ResponseBody
    public boolean evaluateAnswer(@RequestParam(value = "mark", required = true) Mark mark,
                                  @RequestParam(value = "answer_id", required = true) long answerID) {
        Answer answer = answerService.selectById(answerID);
        answer.setMark(mark);
        boolean result = answerService.update(answer);
        List<Answer> answers = answerService.getAnswersByExamId(answer.getExamId());
        Predicate condition = new Predicate() {
            @Override
            public boolean evaluate(Object sample) {
                return ((Answer)sample).getMark() == Mark.UNDEFINED;
            }
        };

        boolean existsUnmarked = CollectionUtils.exists(answers, condition);
        if(!existsUnmarked) {
            examinationService.finishExamination(answer.getExamId());
        }
        return result;
    }

}
