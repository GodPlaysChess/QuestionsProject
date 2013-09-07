package examination.DataLayer.dao;

import examination.DataLayer.models.Answer;
import examination.DataLayer.models.enums.AnswerStatus;
import examination.DataLayer.models.enums.Mark;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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

        boolean inserted = answerDAO.insert(answer);
        assertTrue(answer.getId() > 0);
        assertTrue(inserted);

        long id = answer.getId();

        /* get exam */
        answer = answerDAO.selectById(id);
        Assert.assertNotNull(answer);

        Answer answer1 = answerDAO.getAnswerByQuestionId(1, 1);
        assertNotNull(answer1);

        /* delete exam */
        answerDAO.delete(id);
        assertNull(answerDAO.selectById(id));

    }
}
