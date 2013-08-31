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
    @Autowired
    private QuestionDAO questionDAO;
    private static final Logger log = Logger.getLogger(TestExaminationDao.class);

    @Test
    public void test() {
        /* INSERT course */
        Course course = new Course();
        GradingSystem gradingSystem = new GradingSystem();
        course.setGradingSystem(gradingSystem);
        course.setExamQuestionsNumber(3);
        List<Question> questions = new ArrayList<Question>(1);
        questions.add(questionDAO.selectById(1));  // Change to getRandomQuestion()
        questions.add(questionDAO.selectById(2));  // when this method is finished

        boolean inserted = courseDAO.insert(course);
        assertTrue(course.getId() > 0);
        assertTrue(inserted);

        long id = course.getId();

        /* GET course */
        course = courseDAO.selectById(id);
        assertNotNull(course);

        /* DELETE course */
        courseDAO.delete(id);
        assertNull(courseDAO.selectById(id));
    }
}
