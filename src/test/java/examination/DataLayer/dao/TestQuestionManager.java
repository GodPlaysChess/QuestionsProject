package examination.DataLayer.dao;


import examination.DataLayer.models.Question;
import examination.DataLayer.models.enums.QuestionType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class TestQuestionManager extends AbstractTest<Question> {

    @Autowired
    private QuestionDAO questionDAO;

    @Test
    public void test() {

        /* ADD Question  */
        Question question = new Question();
        question.setText("this message must be deleted");
        question.setType(QuestionType.RADIOBUTTON);

        baseCheck(question, questionDAO);

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
        for (long id : ids){
            questionDAO.delete(id);
        }
    }

}
