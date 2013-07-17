package Service.dao;

import Service.models.BaseModel;
import Service.models.Question;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Repository;

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

    @Override
    public boolean addQuestion(Question question) {
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

    @Override
    public Question getRandomQuestion() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Question getQuestion(Long QuestionID) {
        createFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        Question question = null;
        try {
            tx = session.beginTransaction();
            question = (Question)session.load(Question.class, QuestionID);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return question;
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
    public List<Question> listQuestions() {
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
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /* Method to UPDATE text of the question */
    @Override
    public boolean updateQuestion(Question question) {
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
    @Override
    public boolean deleteQuestion(Long QuestionID) {
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
    public void insert(BaseModel model) {
        addQuestion((Question) model);
    }

    @Override
    public List<Question> selectList(int offset, int limit) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean update(BaseModel model) {
        updateQuestion((Question) model);
        return true;
    }

    @Override
    public boolean delete(long id) {
        deleteQuestion(id);
        return true;
    }
}


