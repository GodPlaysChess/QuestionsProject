package examination.DataLayer.dao;

import examination.DataLayer.models.Course;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseDAOImpl extends BaseDAOImpl implements CourseDAO {

    @Override
    public Course selectById(long id) {
        return selectById(id, Course.class);
    }

    @Override
    public boolean insert(Course course) {
        return super.insert(course);
    }

    @Override
    public List<Course> selectList(long offset, int limit) {
        return selectList(offset, limit, Course.class);
    }


    @Override
    public boolean update(Course model) {
        return super.update(model);
    }

    @Override
    public boolean delete(long id) {
        return delete(id, Course.class);
    }
}
