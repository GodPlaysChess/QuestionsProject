package examination.DataLayer.dao;

import examination.DataLayer.models.Answer;
import examination.DataLayer.models.enums.AnswerStatus;
import examination.DataLayer.models.enums.Mark;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class TestAnswerDAO extends AbstractTest<Answer> {

    @Autowired
    private AnswerDAO answerDAO;

    @Test
    public void test() {
        Answer answer = new Answer();
        answer.setStudentId(1);
        answer.setQuestionId(1);
        answer.setExamId(1);
        answer.setText("This text must be deleted");
        answer.setIntAnswer(1);
        answer.setMark(Mark.getByValue(1));
        answer.setAnswerStatus(AnswerStatus.APPROVED);
        answer.setTimeStart(new Date());

        baseCheck(answer, answerDAO);

    }
}
