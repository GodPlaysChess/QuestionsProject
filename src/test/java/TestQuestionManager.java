import examination.DataLayer.dao.QuestionDAO;
import examination.DataLayer.models.Question;
import examination.DataLayer.models.enums.QuestionType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class TestQuestionManager {

    @Autowired
    private QuestionDAO questionDAO;

    @Test
    public void test() {

        /* ADD Questions - OK */

        Question question = new Question();
        question.setText("this message must be deleted");
        question.setType(QuestionType.RADIOBUTTON);
        boolean result = questionDAO.insert(question);
        assertTrue(result);

        Question quest = questionDAO.selectById(question.getId());
        assertNotNull(quest);

        questionDAO.delete(quest.getId());
        Question questDeleted = questionDAO.selectById(question.getId());
        assertNull(questDeleted);


        List<Question> questionList = new ArrayList<>();
        List<Long> ids = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Question q = new Question();
            q.setText("to insert # " + i);
            q.setType(QuestionType.CHECKBOX);
            questionList.add(q);
            ids.add(q.getId());
        }
        List<Question> selectedQuestions = questionDAO.selectList((int)
                questionList.get(0).getId(), 4);
        assertNotNull(selectedQuestions);
        List<Question> selectedByIdQuestions = questionDAO.selectList(ids);
        assertNotNull(selectedQuestions);

        boolean deleted = questionDAO.deleteList(selectedByIdQuestions.get(0).getId(), 4);
        assertTrue(deleted);









   /*   *//* Update Question's records *//*
        //questionDAO.updateQuestion(new Question("geo", "what?"));

        Question q = questionDAO.getQuestion(questionNum);
        System.out.println(q);
*/
      /* Delete an Question from the database
      *  DELETE - OK.. do not know how to write a test here
      *  mb make deleteQuestion() to return something */
        //   questionDAO.deleteQuestion(4l);

      /* List down new list of the Questions */
    /*        List questionList = questionDAO.listQuestions();
        for (Object question : questionList) {
            if (question instanceof Question) {
                System.out.println(question);
            }
        }*/


    }

    /*Session session = factory.openSession();
    Transaction tx = null;
    Integer QuestionID = null;
    try {
        tx = session.beginTransaction();
        QuestionID = (Integer) session.save(question);
        tx.commit();
    } catch (HibernateException e) {
        if (tx != null) tx.rollback();
        e.printStackTrace();
    } finally {
        session.close();
    }
    return QuestionID;*/
}
