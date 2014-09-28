package examination.DataLayer.dao;

import examination.DataLayer.models.UserRole;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import java.util.List;

/**
 * author: a.savanovich
 * Date: 29.09.14
 * Time: 1:01
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class UserRoleDAOImpl extends BaseDAOImpl implements UserRoleDAO {
    @Nullable
    @Override
    public UserRole selectById(long id) {
        return super.selectById(id, UserRole.class);
    }

    @Override
    public boolean insert(UserRole model) {
        return super.insert(model);
    }

    @Override
    public List<UserRole> selectList(long offset, int limit) {
        return selectList(offset, limit, UserRole.class);
    }

    @Override
    public boolean update(UserRole model) {
        return super.update(model);
    }

    @Override
    public boolean delete(long id) {
        return delete(id, UserRole.class);
    }
}
