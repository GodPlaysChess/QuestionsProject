package Service;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;

@Repository
public class ManageQuestionImpl implements ManageQuestion {

    private static SessionFactory factory;

    @Override
    public Integer addQuestion(Question question) {
        Session session = factory.openSession();
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
        return QuestionID;
    }

    @Override
    public Question getRandomQuestion() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Question getQuestion(Integer QuestionID) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /* Method to READ all questions */
    @Override
    public List listQuestions() {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List Questions = session.createQuery("FROM Question").list();
            tx.commit();
            return Questions;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    /* Method to UPDATE text of the question */
    @Override
    public void updateQuestion(Question question) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Question Question =
                    (Question) session.get(Question.class, QuestionID);
            Question.setText(question.getText());
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
    @Override
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


