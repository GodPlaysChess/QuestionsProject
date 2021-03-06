package examination.DataLayer.dao;

import examination.DataLayer.models.Exam;
import examination.DataLayer.models.enums.ExamStatus;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

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
    public List<Exam> getInevaluatedExams(long courseId) {
        List<Exam> result;
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(Exam.class);
            cr.add(Restrictions.eq("examStatusCode", ExamStatus.NOT_CHECKED.getCode()));
            if (courseId > 0) {
                cr.add(Restrictions.eq("courseId", courseId));
            }
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
