package examination.QuestionService;

import examination.QuestionService.models.QuestionInfo;
import examination.DataLayer.models.Question;
import examination.DataLayer.dao.QuestionDAO;
import examination.DataLayer.dao.CourseDAO;
import examination.DataLayer.dao.ExamDAO;
import examination.DataLayer.models.Course;
import examination.DataLayer.models.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import examination.BaseModelUtils;
import examination.utils.ListUtils;

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
    private QuestionDAO questionDAO;
    @Autowired
    ExamDAO examDAO;

    @Override
    public QuestionInfo start(long studentId, long courseId) {
        Course course = courseDAO.selectById(courseId);
        Exam exam = new Exam();
        exam.setCourseId(courseId);
        exam.setStudentId(studentId);
        exam.setTimeStart(new Date());
        List<Question> questions = questionDAO.selectList(course.getQuestionsIds());
        if (questions == null || questions.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Collections.shuffle(questions);
        questions = ListUtils.subList(questions, 0, course.getExamQuestionsNumber());
        exam.setQuestionIds(BaseModelUtils.createIdsList(questions));
        Question firstQuestion = questions.get(0);
        exam.setCurrentQuestion(firstQuestion.getId());
        examDAO.insert(exam);
        QuestionInfo questionInfo = new QuestionInfo();
        questionInfo.setExam(exam);
        questionInfo.setQuestion(firstQuestion);
        return questionInfo;
    }

    @Override
    public QuestionInfo next(long examenId) {
        Exam exam = examDAO.selectById(examenId);
        int index = exam.getQuestionIds().indexOf(exam.getCurrentQuestion());
        Question currentQuestion = null;
        if (index < exam.getQuestionIds().size()) {
            exam.setCurrentQuestion(index + 1);
            currentQuestion = questionDAO.selectById(exam.getCurrentQuestion());
        } else {
            exam.setCurrentQuestion(-1);
        }
        QuestionInfo questionInfo = new QuestionInfo();
        questionInfo.setExam(exam);
        questionInfo.setQuestion(currentQuestion);
        return questionInfo;
    }

    @Override
    public QuestionInfo current(long examenId) {
        Exam exam = examDAO.selectById(examenId);
        Question currentQuestion = questionDAO.selectById(exam.getCurrentQuestion());
        QuestionInfo questionInfo = new QuestionInfo();
        questionInfo.setExam(exam);
        questionInfo.setQuestion(currentQuestion);
        return questionInfo;
    }

    @Override
    public void finish(long examenId) {
        Exam exam = examDAO.selectById(examenId);
        exam.setCurrentQuestion(-1);
        exam.setTimeFinish(new Date());
        examDAO.update(exam);
    }

    @Override
    public List<Exam> getCurrentExams(long studentId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
