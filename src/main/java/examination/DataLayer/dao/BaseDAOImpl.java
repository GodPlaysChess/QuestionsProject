package examination.DataLayer.dao;


import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;

import javax.annotation.PostConstruct;
import java.util.List;


public abstract class BaseDAOImpl {

    protected SessionFactory factory;
    protected static final Logger log = Logger.getLogger(QuestionDAOImpl.class);

    @PostConstruct
    private void createFactory() {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            log.error("Failed to create session factory: ", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public <T> List<T> selectList(long offset, int limit, Class<T> clazz) {
        List<T> result;
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(clazz);
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

}
