package examination.DataLayer.dao;

import examination.DataLayer.models.Exam;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author: a.savanovich
 * Date: 15.07.13
 * Time: 17:00
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class ExamDAOImpl implements ExamDAO {
    @Override
    public boolean insert(Exam exam) {
        //To change body of implemented methods use File | Settings | File Templates.
        return false;
    }

    @Override
    public Exam selectById(long id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Exam> selectList(int offset, int limit) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean update(Exam model) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean delete(long id) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
