
import examination.DataLayer.dao.QuestionDAO;
import examination.DataLayer.dao.QuestionDAOImpl;
import examination.DataLayer.models.Question;
import examination.DataLayer.models.enums.QuestionType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/spring/applicationContext.xml"})
public class TestQuestionManager {

    @Autowired
    private QuestionDAO questionDAO;


    @Test
    public void test() {

        /* ADD Question  */
        Question question = new Question();
        question.setText("this message must be deleted");
        question.setType(QuestionType.RADIOBUTTON);
        boolean result = questionDAO.insert(question);
        assertTrue(result);

        /* GET Question */
        Question quest = questionDAO.selectById(question.getId());
        assertNotNull(quest);

        /* DELETE Question */
        questionDAO.delete(quest.getId());
        Question questDeleted = questionDAO.selectById(question.getId());
        assertNull(questDeleted);


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

        /* DELETE LIST with OFFSET Question */
        boolean deleted = questionDAO.deleteList(ids.get(0), 3);
        assertTrue(deleted);
    }

}
