package Service;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import java.util.Iterator;
import java.util.List;

public class ManageQuestion {
    private static SessionFactory factory;

    public static void main(String[] args){
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        ManageQuestion MQ = new ManageQuestion();

        /*Integer empID1 = MQ.addQuestion("Zara", "Ali", 1000);
        Integer empID2 = MQ.addQuestion("Daisy", "Das", 5000);
        Integer empID3 = MQ.addQuestion("John", "Paul", 10000);*/

        MQ.listQuestions();

      /* Update Question's records */
        //MQ.updateQuestion(empID1, 5000);

      /* Delete an Question from the database */
        //MQ.deleteQuestion(empID2);

      /* List down new list of the Questions */
        MQ.listQuestions();

    }

    public Integer addQuestion(String type, String text) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer QuestionID = null;
        try {
            tx = session.beginTransaction();
            Question Question = new Question(type, text);
            QuestionID = (Integer) session.save(Question);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return QuestionID;
    }

    /* Method to READ all questions */
    public void listQuestions() {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List Questions = session.createQuery("FROM Question").list();
            for (Iterator iterator =
                         Questions.iterator(); iterator.hasNext(); ) {
                Question Question = (Question) iterator.next();
                System.out.print("Type: |" + Question.getType());
                System.out.print("  Text: " + Question.getText());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to UPDATE text of the question */
    public void updateQuestion(Integer QuestionID, String newText) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Question Question =
                    (Question) session.get(Question.class, QuestionID);
            Question.setText(newText);
            session.update(Question);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to DELETE the Question from the records */
    public void deleteQuestion(Integer QuestionID) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Question Question =
                    (Question) session.get(Question.class, QuestionID);
            session.delete(Question);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
