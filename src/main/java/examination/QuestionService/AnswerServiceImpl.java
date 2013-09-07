package examination.QuestionService;

import examination.DataLayer.dao.AnswerDAO;
import examination.DataLayer.dao.AnswerDAOImpl;
import examination.DataLayer.models.Answer;
import examination.DataLayer.models.Exam;
import examination.DataLayer.models.Question;
import examination.DataLayer.models.enums.AnswerStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;


public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerDAO answerDAO;
    @Autowired
    private ExaminationService examinationService;

    @Override
    public boolean save(Answer answer) {
        answer.setAnswerStatus(AnswerStatus.getByValue(1));
        // get the exam
        Exam currentExam = examinationService.selectById(answer.getExamId());
        List<Question> questions = currentExam.getQuestions();
        long currentQuestionId = answer.getQuestionId();
        int index = 0;
        for (; index < questions.size(); index++) {
            if (questions.get(index).getId() == currentQuestionId) {
                --index;
                break;
            }
        }
        long prevQuestionId = questions.get(index).getId();
        Date prevTimeFinish = null;
        if (index > 0) {
            prevTimeFinish = answerDAO.getAnswerByQuestionId(currentExam.getId(),
                    prevQuestionId).getTimeFinish();
        } else {
            prevTimeFinish = currentExam.getTimeStart();
        }

        answer.setTimeStart(prevTimeFinish);
        answer.setTimeFinish(new Date());
        return answerDAO.insert(answer);
    }

    @Override
    public Answer selectById(long id) {
        return answerDAO.selectById(id);
    }



    /*   выставляет timeStart, timeFinish
         answerStatus       */

}
