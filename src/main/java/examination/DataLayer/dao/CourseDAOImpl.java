package examination.DataLayer.dao;

import examination.DataLayer.models.Course;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseDAOImpl extends BaseDAOImpl implements CourseDAO {


    @Override
    public Course selectById(long id) {
        return (Course)currentSession().get(Course.class, id);
    }

    @Override
    public boolean insert(Course course) {
        currentSession().save(course);
        return true;
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
        currentSession().update(model);
        return true;
    }

    @Override
    public boolean delete(long id) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
