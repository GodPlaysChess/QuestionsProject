package examination.DataLayer.dao;

import examination.DataLayer.models.Answer;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import java.util.List;

@Repository
public class AnswerDAOImpl extends BaseDAOImpl implements AnswerDAO {

    @Nullable
    @Override
    public Answer selectById(long id) {
        Session session = factory.openSession();
        Transaction tx = null;
        Answer answer = null;
        try {
            tx = session.beginTransaction();
            answer = (Answer) session.get(Answer.class, id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.error("Get exam error: ", e);
        } finally {
            session.close();
        }
        return answer;
    }


    @Override
    public boolean insert(Answer model) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(model);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.error("Add error", e);
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    @Override
    public List<Answer> selectList(long offset, int limit) {
        List<Answer> result;
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(Answer.class);
            cr.setFirstResult((int) offset);      // BAD Since cast offset to int!!
            cr.setMaxResults(limit);
            result = cr.list();
            tx.commit();
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.error("Select list error: ", e);
        }
        return null;
    }

    @Override
    public boolean deleteList(long offset, int limit) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean update(Answer model) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            /* not everything is updated ??? */
            Answer answer = (Answer) session.get(Answer.class, model.getId());
            answer.setTimeFinish(model.getTimeFinish());
            answer.setTimeStart(model.getTimeStart());
            answer.setText(model.getText());
            answer.setAnswerStatus(model.getAnswerStatus());
            answer.setMark(model.getMark()); // previously mark was not updated
            session.update(answer);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.error("Update answer error: ", e);
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    @Override
    public boolean delete(long id) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Answer answer =
                    (Answer) session.get(Answer.class, id);
            session.delete(answer);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.error("Delete answer error: ", e);
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    @Override
    public Answer getAnswerByQuestionId(long examId, long questionId) {
        Session session = factory.openSession();
        Transaction tx = null;
        Answer answer = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Answer.class);
            criteria.add(Restrictions.eq("examId", examId));
            criteria.add(Restrictions.eq("questionId", questionId));
            List<Answer> answers = criteria.list();
            if (answers.size() > 0) {
                answer = answers.get(0);
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.error("Get exam error: ", e);
        } finally {
            session.close();
        }
        return answer;

    }

    //need to write test for it
    @Override
    public List<Answer> getInevaluatedAnswers() {
        List<Answer> result;
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(Answer.class);
            cr.add(Restrictions.eq("markCode", 0));
            cr.add(Restrictions.eq("answerStatusCode", 1));
            result = cr.list();
            tx.commit();
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.error("Select list error: ", e);
        }
        return null;
    }

    @Override
    public List<Answer> getAnswerByExamId(long examId) {
        List<Answer> result;
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Answer.class);
            criteria.add(Restrictions.eq("examId", examId));
            result = criteria.list();
            tx.commit();
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.error("Get exam error: ", e);
        } finally {
            session.close();
        }
        return null;

    }


}

