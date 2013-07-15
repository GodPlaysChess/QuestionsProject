package QuestionService;

import Service.Question;
import Service.ManageQuestion;
import Service.dao.CourseDAO;
import Service.dao.ExamDAO;
import Service.models.Course;
import Service.models.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Service.BaseModelUtils;
import utils.ListUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * author: a.savanovich
 * Date: 15.07.13
 * Time: 16:27
 * To change this template use File | Settings | File Templates.
 */
@Service
public class ExaminationServiceImpl implements ExaminationService {
    @Autowired
    private CourseDAO courseDAO;
    @Autowired
    private ManageQuestion manageQuestion;
    @Autowired
    ExamDAO examDAO;

    @Override
    public Question start(long studentId, long courseId) {

        Course course = courseDAO.selectById(courseId);
        Exam exam = new Exam();
        exam.setCourseId(courseId);
        exam.setStudentId(studentId);
        exam.setTimeStart(new Date());
        List<Question> questions = manageQuestion.selectList(course.getQuestionsIds());
        if (questions == null || questions.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Collections.shuffle(questions);
        questions = ListUtils.subList(questions, 0, course.getExamenQuestionsNumber());
        exam.setQuestionIds(BaseModelUtils.createIdsList(questions));
        Question firstQuestion = questions.get(0);
        exam.setCurrentQuestion(firstQuestion.getId());
        examDAO.insert(exam);
        return firstQuestion;
    }

    @Override
    public Question next(long examenId) {
        Exam exam = examDAO.selectById(examenId);
        int index = exam.getQuestionIds().indexOf(exam.getCurrentQuestion());
        if (index < exam.getQuestionIds().size()) {
            exam.setCurrentQuestion(index + 1);
            Question currentQuestion = manageQuestion.getQuestion(exam.getCurrentQuestion());
            return currentQuestion;
        } else {
            exam.setCurrentQuestion(-1);
            return null;
        }
    }

    @Override
    public void finish(long examenId) {
        Exam exam = examDAO.selectById(examenId);
        exam.setCurrentQuestion(-1);
        exam.setTimeFinish(new Date());
        examDAO.update(exam);
    }
}
