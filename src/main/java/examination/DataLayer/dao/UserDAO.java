package examination.DataLayer.dao;

import examination.DataLayer.models.User;

/**
 * author: a.savanovich
 * Date: 23.09.14
 * Time: 8:55
 * To change this template use File | Settings | File Templates.
 */
public interface UserDAO extends BaseDAO<User> {
    User selectByName(String username);
}
