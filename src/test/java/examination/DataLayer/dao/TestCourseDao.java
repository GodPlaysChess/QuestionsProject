package examination.DataLayer.dao;

import examination.DataLayer.models.Exam;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/spring/applicationContext.xml"})
public class TestCourseDao {

    @Autowired
    private ExamDAO examDAO;
    private static final Logger log = Logger.getLogger(TestExaminationDao.class);

    @Test
    public void test() {
        /* Insert Examination */
        Exam exam = new Exam();
        exam.setCourseId(1);
        exam.setStudentId(2);
        exam.setTimeStart(new Date());
        exam.setTimeFinish(new Date());
        exam.setCurrentQuestion(3);
        //exam.setQuestionIds()
        boolean inserted = examDAO.insert(exam);
        assertTrue(inserted);

        long id = exam.getId();

        /* get exam */
        exam = examDAO.selectById(id);
        assertNotNull(exam);

        /* delete exam */
        examDAO.delete(id);
        assertNull(examDAO.selectById(id));


    }
}
