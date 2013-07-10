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

public class TestQuestionManager {
    private static SessionFactory factory;

    @Test
    public void test() {

 /*       try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {

        }*/

        ManageQuestion MQ = new ManageQuestionImpl();
        Integer empID3 = MQ.addQuestion(new Question("geo", "where?"));

      /* Update Question's records */
        //MQ.updateQuestion(empID1, 5000);

      /* Delete an Question from the database */
        //MQ.deleteQuestion(empID2);

      /* List down new list of the Questions */
        List questionList = MQ.listQuestions();
        for (Object question : questionList) {
            if (question instanceof Question) {
                System.out.println(question);
            }
        }



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
