package examination.DataLayer.dao;

import examination.DataLayer.models.Course;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseDAOImpl implements CourseDAO {
    @Override
    public Course selectById(long id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean insert(Course course) {
        //To change body of implemented methods use File | Settings | File Templates.
        return false;
    }

    @Override
    public List<Course> selectList(long offset, int limit) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean deleteList(long offset, int limit) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean update(Course model) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean delete(long id) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
