package examination.DataLayer.dao;

import examination.DataLayer.models.BaseModel;

import javax.annotation.Nullable;
import java.util.List;

public interface BaseDAO <T extends BaseModel> {
    @Nullable
    T selectById(long id);

    boolean insert(T model);

    List<T> selectList(long offset, int limit);

    boolean deleteList(long offset, int limit);

    boolean update(T model);

    boolean delete(long id);
}
