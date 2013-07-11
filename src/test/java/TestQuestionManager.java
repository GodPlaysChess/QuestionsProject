import Service.ManageQuestion;
import Service.ManageQuestionImpl;
import Service.Question;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class TestQuestionManager {

    @Test
    public void test() {

        ManageQuestion MQ = new ManageQuestionImpl();

        /* ADD Questions - OK */
        Integer questionNum = MQ.addQuestion(new Question("geo", "when?"));
        System.out.println(questionNum);
        assertTrue(questionNum > 0);

      /* Update Question's records */
        //MQ.updateQuestion(new Question("geo", "what?"));

        Question q = MQ.getQuestion(questionNum);
        System.out.println(q);

      /* Delete an Question from the database
      *  DELETE - OK.. do not know how to write a test here
      *  mb make deleteQuestion() to return something */
        MQ.deleteQuestion(questionNum);





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
