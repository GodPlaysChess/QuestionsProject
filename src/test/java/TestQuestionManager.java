import Service.ManageQuestion;
import Service.ManageQuestionImpl;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class TestQuestionManager {
    private static SessionFactory factory;

    @Test
    public void test() {

        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        ManageQuestion MQ = new ManageQuestionImpl();

        //Integer empID3 = MQ.addQuestion("John", "Paul", 10000);

        MQ.listQuestions();

      /* Update Question's records */
        //MQ.updateQuestion(empID1, 5000);

      /* Delete an Question from the database */
        //MQ.deleteQuestion(empID2);

      /* List down new list of the Questions */
        MQ.listQuestions();


    }
}
