package examination.DataLayer.dao;

import examination.DataLayer.models.BaseModel;

import java.util.List;

/**
 * author: a.savanovich
 * Date: 14.07.13
 * Time: 15:15
 * To change this template use File | Settings | File Templates.
 */
public interface BaseDAO <T extends BaseModel> {
    T selectById(long id);

    boolean insert(T model);

    List<T> selectList(long offset, int limit);

    boolean deleteList(long offset, int limit);

    boolean update(T model);

    boolean delete(long id);
}
