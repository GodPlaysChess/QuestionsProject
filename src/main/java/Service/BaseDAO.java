package Service;

import java.util.List;

/**
 * author: a.savanovich
 * Date: 14.07.13
 * Time: 15:15
 * To change this template use File | Settings | File Templates.
 */
public interface BaseDAO {
    Object selectById(long id);

    List selectList(int offset, int limit);

    boolean update(Object model);

    boolean delete(long id);
}
