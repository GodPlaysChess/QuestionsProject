package examination.DataLayer.dao;


import examination.DataLayer.models.Question;
import examination.DataLayer.models.enums.QuestionType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestQuestionManager extends AbstractTest<Question> {

    @Autowired
    private QuestionDAO questionDAO;

    @Test
    public void test() {

        /* ADD Question  */
        Question question = new Question();
        question.setText("this message must be deleted");
        question.setType(QuestionType.RADIOBUTTON);
        boolean inserted = questionDAO.insert(question);
        assertTrue(question.getId() > 0);
        assertTrue(inserted);

        long id = question.getId();

        /* get */
        question = questionDAO.selectById(id);
        assertNotNull(question);

        /* update */
        question.setText("updated");
        questionDAO.update(question);
        question = questionDAO.selectById(id);
        assertEquals(question.getText(), "updated");

        /* delete */
        questionDAO.delete(id);
        assertNull(questionDAO.selectById(id));

        /* GET LIST with OFFSET Question */
        List<Long> ids = new ArrayList<Long>();
        for (int i = 0; i < 3; i++) {
            Question q = new Question();
            q.setText("to insert # " + i);
            q.setType(QuestionType.RADIOBUTTON);
            questionDAO.insert(q);
            ids.add(q.getId());
        }
        List<Question> selectedQuestions = questionDAO.selectList(
                ids.get(0), 3);
        assertNotNull(selectedQuestions);

        /* GET LIST Question by ids */
        List<Question> selectedByIdQuestions = questionDAO.selectList(ids);
        assertNotNull(selectedByIdQuestions);

        /* Get Random Question */
        Question question1 = questionDAO.getRandomQuestion();
        assertNotNull(question1);




        /* Rollback */
        for (long i : ids) {
            questionDAO.delete(i);
        }
    }

}
