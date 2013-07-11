package Service;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.hibernate.impl.SessionFactoryImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ManageQuestionImpl implements ManageQuestion {
    private SessionFactory factory;

    private void createFactory(){
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    @Override
    public Integer addQuestion(Question question) {
        createFactory();
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
        createFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        String text = null;
        String type = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createSQLQuery("SELECT type, text FROM Questions WHERE " +
                    "id = " + QuestionID);
            List list = query.list();
            type = String.valueOf(list.get(0)[0]);
            text = list.get(0);*/       //todo finish the method
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return new Question(type, text);
    }

/*
        String SQL_QUERY = "select
        max(FIELD_NAME)from Insurance insurance";
        Query query = sess.createQuery(SQL_QUERY);
        List list = query.list();
        System.out.println("Max
                Invested Amount: " + list.get(0));
        */


    /* Method to READ all questions */
    @Override
    public List listQuestions() {
        createFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List Questions = session.createSQLQuery("SELECT * FROM Question").list();
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
        createFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Question Question =
                    (Question) session.get(Question.class, question.getId());
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
        createFactory();
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


