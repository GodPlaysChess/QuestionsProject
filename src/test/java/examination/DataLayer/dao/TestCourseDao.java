package examination.DataLayer.dao;

import examination.DataLayer.models.Course;
import examination.DataLayer.models.Exam;
import examination.DataLayer.models.GradingSystem;
import examination.DataLayer.models.Question;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/spring/applicationContext.xml"})
public class TestCourseDao {

    @Autowired
    private CourseDAO courseDAO;
    private static final Logger log = Logger.getLogger(TestExaminationDao.class);

    @Test
    public void test() {
        /* Insert Course */
        Course course = new Course();
        GradingSystem gradingSystem = new GradingSystem();
        course.setGradingSystem(gradingSystem);
        course.setExamQuestionsNumber(5);
        List<Question> questions = new ArrayList<Question>(1);

        boolean inserted = courseDAO.insert(course);
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
