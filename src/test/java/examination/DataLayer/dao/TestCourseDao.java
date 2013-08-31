package examination.DataLayer.dao;

import examination.DataLayer.models.Course;
import examination.DataLayer.models.GradingSystem;
import examination.DataLayer.models.Question;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TestCourseDao  extends AbstractTest<Course> {

    @Autowired
    private CourseDAO courseDAO;
    @Autowired
    private QuestionDAO questionDAO;

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

        baseCheck(course, courseDAO);
    }
}
