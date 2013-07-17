import Service.dao.QuestionDAO;
import Service.dao.QuestionDAOImpl;
import Service.models.Question;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestQuestionManager {

    @Test
    public void test() {

        QuestionDAO MQ = new QuestionDAOImpl();

        /* ADD Questions - OK */

        Question question = new Question();
        question.setText("testDel");
        question.setTypeCode(1);
        boolean result = MQ.addQuestion(question);

        assertTrue(result);

        Question quest = MQ.getQuestion(question.getId());
        assertTrue(quest != null);

        MQ.deleteQuestion(quest.getId());
        Question questDeleted = MQ.getQuestion(question.getId());
        assertTrue(questDeleted == null);







   /*   *//* Update Question's records *//*
        //MQ.updateQuestion(new Question("geo", "what?"));

        Question q = MQ.getQuestion(questionNum);
        System.out.println(q);
*/
      /* Delete an Question from the database
      *  DELETE - OK.. do not know how to write a test here
      *  mb make deleteQuestion() to return something */
        //   MQ.deleteQuestion(4l);

      /* List down new list of the Questions */
    /*        List questionList = MQ.listQuestions();
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
