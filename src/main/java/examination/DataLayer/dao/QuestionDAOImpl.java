package examination.DataLayer.dao;

import examination.DataLayer.models.Question;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class QuestionDAOImpl implements QuestionDAO {
    private SessionFactory factory;

    private void createFactory() {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private boolean addQuestion(Question question) {
        createFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(question);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    private Question getQuestion(long questionID) {
        createFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        Question question = null;
        try {
            tx = session.beginTransaction();
            question = (Question) session.get(Question.class, questionID);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return question;
    }

    /* Method to READ all questions */
    private List<Question> listQuestions() {
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

    @Override
    public List<Question> selectList(List<Long> questionIds) {
        createFactory();
        List<Question> result = new LinkedList<Question>();
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (long id : questionIds) {
                result.add(getQuestion(id));
            }
            tx.commit();
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            //log4j.error()
        }
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /* Method to UPDATE text of the question */

    private boolean updateQuestion(Question question) {
        createFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(question);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        return true;
    }
/*
    UPDATE questions
    SET text = {question.text}
    .....
    where id = {question.id}*/

    /* Method to DELETE the Question from the records */

    private boolean deleteQuestion(long questionID) {
        createFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Question Question =
                    (Question) session.get(Question.class, questionID);
            session.delete(Question);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    @Override
    public Question selectById(long id) {
        return getQuestion(id);
    }

    @Override
    public boolean insert(Question model) {
        return addQuestion(model);
    }

    @Override
    public List<Question> selectList(long offset, int limit) {
        createFactory();
        List<Question> result = new LinkedList<Question>();
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (long id = offset - 1; id < offset + limit - 2; id++) {
                result.add(getQuestion(id));
            }
            tx.commit();
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            //log4j.error()
        }
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean deleteList(long offset, int limit) {
        createFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (long id = offset - 1; id < offset + limit - 2; id++) {
                deleteQuestion(id);
            }
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            //log4j.error()
        }
        return false;
    }

    @Override
    public boolean update(Question model) {
        return updateQuestion(model);
    }

    @Override
    public boolean delete(long id) {
        return deleteQuestion(id);
    }
}


