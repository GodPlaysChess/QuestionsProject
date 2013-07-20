package examination.DataLayer.dao;

import examination.DataLayer.models.Exam;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public List<Exam> selectList(long offset, int limit) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean deleteList(long offset, int limit) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
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
