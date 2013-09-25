package examination.DataLayer.dao;

import examination.DataLayer.models.Exam;
import examination.DataLayer.models.enums.ExamStatus;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.management.Query;
import java.util.List;

@Repository
public class ExamDAOImpl extends BaseDAOImpl implements ExamDAO {

    @Override
    public boolean insert(Exam exam) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(exam);
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
    public Exam selectById(long id) {
        Session session = factory.openSession();
        Transaction tx = null;
        Exam exam = null;
        try {
            tx = session.beginTransaction();
            exam = (Exam) session.get(Exam.class, id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.error("Get exam error: ", e);
        } finally {
            session.close();
        }
        return exam;
    }

    @Override
    public List<Exam> selectList(long offset, int limit) {
        List<Exam> result;
        Session session = factory.openSession();
        Transaction tx = null;
        try {
               /*
               *
               * WRITE CODE HERE
               *
               * */
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.error("Select list error: ", e);
        }
        return null;
    }

    public List<Exam> selectList(List<Long> examIds) {
        List<Exam> result;
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(Exam.class);
            cr.add(Restrictions.in("id", examIds)); //perhaps not correct was "idexams"
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
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "DELETE FROM Exam WHERE id > :offset " +
                    "AND id < :maxnum";
            Query query = (Query) session.createSQLQuery(sql).addEntity(Exam.class)
                    .setParameter("offset", offset).setParameter("maxnum", offset + limit);

            for (long id = offset; id < offset + limit; id++) {
                delete(id);
            }
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.error("Delete list error: ", e);
        }
        return false;
    }

    @Override
    public boolean update(Exam model) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(model);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.error("Update question error: ", e);
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
            Exam exam =
                    (Exam) session.get(Exam.class, id);
            session.delete(exam);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.error("Delete question error: ", e);
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    @Override
    public List<Exam> getCurrentExams(long studentId) {
        List<Exam> result;
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(Exam.class);
            cr.add(Restrictions.eq("studentId", studentId));
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
    public List<Exam> getInevaluatedExams() {
        List<Exam> result;
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(Exam.class);
            cr.add(Restrictions.eq("examStatusCode", 0));
            result = cr.list();
            tx.commit();
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.error("Select list error: ", e);
        }
        return null;
    }
}
