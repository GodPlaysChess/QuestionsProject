package examination.DataLayer.dao;

import examination.DataLayer.models.Course;
import examination.DataLayer.models.GradingSystem;
import examination.DataLayer.models.Question;
import examination.DataLayer.models.enums.QuestionType;
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
        Question q1 = new Question();
        q1.setText("text");
        q1.setType(QuestionType.SIMPLE);
        Question q2 = new Question();
        q2.setText("text");
        q2.setType(QuestionType.SIMPLE);
        questionDAO.insert(q1);
        questionDAO.insert(q2);
        questions.add(questionDAO.selectById(q1.getId()));
        questions.add(questionDAO.selectById(q2.getId()));
        course.setQuestions(questions);

        baseCheck(course, courseDAO);

        questionDAO.delete(q1.getId());
        questionDAO.delete(q2.getId());

    }
}
