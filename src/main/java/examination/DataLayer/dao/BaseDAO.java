package examination.DataLayer.dao;

import examination.DataLayer.models.BaseModel;

import java.util.List;

/**
 * author: a.savanovich
 * Date: 14.07.13
 * Time: 15:15
 * To change this template use File | Settings | File Templates.
 */
public interface BaseDAO {
    BaseModel selectById(long id);

    void insert(BaseModel model);

    List<? extends BaseModel> selectList(int offset, int limit);

    boolean update(BaseModel model);

    boolean delete(long id);
}
