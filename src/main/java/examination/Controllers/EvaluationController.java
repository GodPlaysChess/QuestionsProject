package examination.Controllers;

import examination.DataLayer.models.Answer;
import examination.DataLayer.models.Question;
import examination.DataLayer.models.enums.Mark;
import examination.QuestionService.AnswerService;
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

    //-  FIX -
    @RequestMapping(value = ("/exams_to_evaluate.html"))
    public ModelAndView toEvaluationPage() {
        ModelAndView modelAndView = new ModelAndView("exams_evaluate");
        List<Answer> allAnswers = answerService.getInevaluatedAnswers();
        Set<Long> examIds = new HashSet<Long>();
        for (Answer answer : allAnswers) {
            examIds.add(answer.getExamId());
        }
        modelAndView.addObject("examIds", examIds);
        return modelAndView;
    }

    /* the other option is to use varStatus instead of the map to loop
    * over two Lists simultaneously */
    @RequestMapping(value = {"/evaluate.html"}, method = RequestMethod.POST)
    public ModelAndView beginEvaluation(@RequestParam(value = "examId",
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

    @RequestMapping(value = {"/evaluate.json"})
    @ResponseBody
    public boolean evaluateAnswer(@RequestParam(value = "mark_code", required = true) int markCode,
                                  @RequestParam(value = "answer_id", required = true) long answerID) {
        Answer answer = answerService.selectById(answerID);
        answer.setMarkCode(markCode);
        return answerService.update(answer);
    }

}
