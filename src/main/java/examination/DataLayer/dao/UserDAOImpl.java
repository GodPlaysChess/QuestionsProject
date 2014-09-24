package examination.DataLayer.dao;

import examination.DataLayer.models.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import java.util.List;

/**
 * author: a.savanovich
 * Date: 23.09.14
 * Time: 9:09
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class UserDAOImpl extends BaseDAOImpl implements UserDAO {
    @Nullable
    @Override
    public User selectById(long id) {
        return null;
    }

    @Override
    public boolean insert(User model) {
        return false;
    }

    @Override
    public List<User> selectList(long offset, int limit) {
        return selectList(offset, limit, User.class);
    }

    @Override
    public boolean update(User model) {
        return false;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public User selectByName(String username) {
        Session session = factory.openSession();
        Transaction tx = null;
        User user = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("username", username));
            List<User> users = criteria.list();
            if (users.size() > 0) {
                user = users.get(0);
                user.setAuthorities();
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.error("Get exam error: ", e);
        } finally {
            session.close();
        }
        return user;

    }
}
