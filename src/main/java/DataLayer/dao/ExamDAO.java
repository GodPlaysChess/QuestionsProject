package DataLayer.dao;

import DataLayer.models.Exam;

import java.util.List;

/**
 * author: a.savanovich
 * Date: 15.07.13
 * Time: 16:55
 * To change this template use File | Settings | File Templates.
 */
public interface ExamDAO extends BaseDAO {
    void insert(Exam exam);
    @Override
    Exam selectById(long id);

    @Override
    public List<Exam> selectList(int offset, int limit);
}
