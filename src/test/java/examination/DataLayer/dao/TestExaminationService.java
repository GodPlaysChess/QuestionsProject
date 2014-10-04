package examination.DataLayer.dao;

import examination.DataLayer.models.Course;
import examination.DataLayer.models.GradingSystem;
import examination.DataLayer.models.Question;
import examination.DataLayer.models.enums.QuestionType;
import examination.QuestionService.ExaminationService;
import examination.QuestionService.models.QuestionInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/spring/applicationContext*.xml"})
public class TestExaminationService {

    @Autowired
    private ExaminationService examinationService;
    @Autowired
    private CourseDAO courseDAO;

    @Autowired
    private QuestionDAO questionDAO;
    @Autowired
    private ExamDAO examDAO;

    @Test
    public void test() {
        Course course = new Course();
        GradingSystem gradingSystem = new GradingSystem();
        course.setGradingSystem(gradingSystem);
        course.setExamQuestionsNumber(2);
        List<Question> questions = new ArrayList<Question>(1);
        Question q1 = new Question();
        q1.setText("text");
        q1.setType(QuestionType.SIMPLE);
        Question q2 = new Question();
        q2.setText("text");
        q2.setType(QuestionType.SIMPLE);
        questionDAO.insert(q1);
        questionDAO.insert(q2);
        questions.add(q1);
        questions.add(q2);
        course.setQuestions(questions);
        courseDAO.insert(course);
        long courseId = course.getId();

        QuestionInfo questionInfo1 = examinationService.start(2, courseId);
        assertNotNull(questionInfo1);

        long examId = questionInfo1.getExam().getId();
        QuestionInfo questionInfo2 = examinationService.next(examId);
        assertNotNull(questionInfo2);
        assertNotSame(questionInfo2.getQuestion().getId(), questionInfo1.getQuestion().getId());
        assertEquals(questionInfo1.getExam().getId(), questionInfo2.getExam().getId());

        QuestionInfo questionInfo3 = examinationService.current(examId);
        assertNotNull(questionInfo3);
        assertEquals(questionInfo3.getQuestion().getId(), questionInfo2.getQuestion().getId());
        assertEquals(questionInfo3.getExam().getId(), questionInfo2.getExam().getId());

        examDAO.delete(examId);
        assertNull(examDAO.selectById(examId));

        courseDAO.delete(courseId);
        assertNull(courseDAO.selectById(courseId));

        questionDAO.delete(q1.getId());
        assertNull(questionDAO.selectById(q1.getId()));
        questionDAO.delete(q2.getId());
        assertNull(questionDAO.selectById(q2.getId()));
    }

}
