package Service;

import Service.models.BaseModel;

import java.util.List;

/**
 * author: a.savanovich
 * Date: 14.07.13
 * Time: 15:15
 * To change this template use File | Settings | File Templates.
 */
public interface BaseDAO {
    BaseModel selectById(long id);

    List selectList(int offset, int limit);

    boolean update(BaseModel model);

    boolean delete(long id);
}
