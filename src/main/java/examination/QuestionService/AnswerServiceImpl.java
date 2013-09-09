package examination.QuestionService;

import examination.DataLayer.dao.AnswerDAO;
import examination.DataLayer.models.Answer;
import examination.DataLayer.models.Exam;
import examination.DataLayer.models.Question;
import examination.DataLayer.models.enums.AnswerStatus;
import examination.DataLayer.models.enums.Mark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerDAO answerDAO;
    @Autowired
    private ExaminationService examinationService;


    private boolean save(Answer answer, AnswerStatus answerStatus) {
        answer.setMark(Mark.UNDEFINED);
        answer.setAnswerStatus(answerStatus);
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
        Date prevTimeFinish;
        if (index >= 0) {
            long prevQuestionId = questions.get(index).getId();
            Answer prevAnswer = answerDAO.getAnswerByQuestionId(answer.getExamId(),
                    prevQuestionId);
            prevTimeFinish = prevAnswer.getTimeFinish();

        } else {
            prevTimeFinish = currentExam.getTimeStart();
        }
        answer.setTimeStart(prevTimeFinish);
        answer.setTimeFinish(new Date());
        Answer thisAnswer = answerDAO.selectById(answer.getId());
        if (thisAnswer == null) {
            return answerDAO.insert(answer);
        } else {
            return answerDAO.update(answer);
        }
    }

    @Override
    public boolean manualSave(Answer answer) {
        return save(answer, AnswerStatus.APPROVED);
    }

    @Override
    public Answer selectById(long id) {
        return answerDAO.selectById(id);
    }

    @Override
    public boolean autoSave(Answer answer) {
        return save(answer, AnswerStatus.AUTOSAVE);
    }
}