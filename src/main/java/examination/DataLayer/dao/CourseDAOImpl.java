package examination.DataLayer.dao;

import examination.DataLayer.models.BaseModel;
import examination.DataLayer.models.Course;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author: a.savanovich
 * Date: 15.07.13
 * Time: 16:25
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class CourseDAOImpl implements CourseDAO {
    @Override
    public Course selectById(long id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void insert(BaseModel model) {
        insert((Course) model);
    }

    @Override
    public void insert(Course course) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Course> selectList(int offset, int limit) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean update(BaseModel model) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean delete(long id) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
