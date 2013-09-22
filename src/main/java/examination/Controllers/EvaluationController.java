package examination.Controllers;

import examination.DataLayer.models.Answer;
import examination.DataLayer.models.Question;
import examination.QuestionService.AnswerService;
import examination.QuestionService.ExaminationService;
import examination.QuestionService.ExaminationServiceImpl;
import examination.QuestionService.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;

@Controller
public class EvaluationController {

    @Autowired
    private AnswerService answerService;
    @Autowired
    private ExaminationService examinationService;
    @Autowired
    private QuestionService questionService;


    @RequestMapping(value = ("/exams_to_evaluate.html"))
    public ModelAndView toEvaluationPage() {
        ModelAndView modelAndView = new ModelAndView("exams_evaluate");
        List<Answer> allAnswers = answerService.getInevaluatedAnswers();
        Set<Long> examIds = new HashSet<Long>();
        for (Answer answer : allAnswers) {
            examIds.add(answer.getExamId());
        }/*
        Map<Long, List<Answer>> answersMap = new HashMap<Long, List<Answer>>();
        for (final Answer answer : allAnswers) {
            if (answersMap.containsKey(answer.getExamId())) {
                List<Answer> currentList = answersMap.get(answer.getExamId());
                currentList.add(answer);
                answersMap.put(answer.getExamId(), currentList);
            } else {
                answersMap.put(answer.getExamId(), new ArrayList<Answer>() {{
                    add(answer);
                }});
            }
        }*/
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
        //    modelAndView.addObject("answers", answers);
        List<Long> questions = new ArrayList<Long>();
        for (Answer a : answers) {
            questions.add(a.getQuestionId());
        }
        List<Question> questionList = questionService.selectList(questions);
        //   modelAndView.addObject("questions", questionList);
        Map<Question, Answer> answerMap = new HashMap<Question, Answer>();
        for (int i = 0; i < answers.size(); i++) {
            answerMap.put(questionList.get(i), answers.get(i));
        }
        modelAndView.addObject("aMap", answerMap);
        return modelAndView;
    }

    @RequestMapping(value = {"evaluate_answer.html"}, method = RequestMethod.POST)
    public ModelAndView evaluateAnswer(@RequestParam(value = "mark", required = true) long mark,
                                       @RequestParam(value = "answer", required = true) Answer answer) {
        //get examId
        //save answer
        // if success remove from the inevaluated list
        // show list again
        ModelAndView modelAndView = new ModelAndView("answer_list");
        return modelAndView;
    }

}
