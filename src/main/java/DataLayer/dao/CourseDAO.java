package DataLayer.dao;

import DataLayer.models.Course;

import java.util.List;

/**
 * author: a.savanovich
 * Date: 15.07.13
 * Time: 16:20
 * To change this template use File | Settings | File Templates.
 */
public interface CourseDAO extends BaseDAO {
    @Override
    Course selectById(long id);

    void insert(Course course);

    @Override
    public List<Course> selectList(int offset, int limit);
}
