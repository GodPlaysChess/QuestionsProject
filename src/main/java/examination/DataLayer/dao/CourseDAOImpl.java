package examination.DataLayer.dao;

import examination.DataLayer.models.Course;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseDAOImpl extends BaseDAOImpl implements CourseDAO {

    @Override
    public Course selectById(long id) {
        Session session = factory.openSession();
        Transaction tx = null;
        Course course = null;
        try {
            tx = session.beginTransaction();
            course = (Course) session.get(Course.class, id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.error("Get exam error: ", e);
        } finally {
            session.close();
        }
        return course;
    }

    @Override
    public boolean insert(Course course) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(course);
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
    public List<Course> selectList(long offset, int limit) {
        return selectList(offset, limit, Course.class);
    }


    @Override
    public boolean update(Course model) {
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
            Course course =
                    (Course) session.get(Course.class, id);
            session.delete(course);
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
}
