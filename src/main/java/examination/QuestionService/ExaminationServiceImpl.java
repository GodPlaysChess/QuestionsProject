package examination.QuestionService;

import examination.DataLayer.dao.CourseDAO;
import examination.DataLayer.dao.ExamDAO;
import examination.DataLayer.dao.QuestionDAO;
import examination.DataLayer.models.Course;
import examination.DataLayer.models.Exam;
import examination.DataLayer.models.Question;
import examination.QuestionService.models.QuestionInfo;
import examination.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

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
        List<Question> questions = course.getQuestions();
        if (questions == null || questions.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Collections.shuffle(questions);
        questions = ListUtils.subList(questions, 0, course.getExamQuestionsNumber());
        exam.setQuestions(questions);
        Question firstQuestion = questions.get(0);
        exam.setCurrentQuestion(firstQuestion.getId());
        examDAO.insert(exam);
        QuestionInfo questionInfo = new QuestionInfo();
        questionInfo.setExam(exam);
        questionInfo.setQuestion(firstQuestion);
        return questionInfo;
    }

    @Override
    public QuestionInfo next(long examId) {
        Exam exam = examDAO.selectById(examId);
        int index = getCurrentQuestionIndex(exam.getQuestions(), exam.getCurrentQuestion());
        ++index;
        Question currentQuestion = null;
        if (index < exam.getQuestions().size()) {
            currentQuestion = exam.getQuestions().get(index);
            exam.setCurrentQuestion(currentQuestion.getId());
            examDAO.update(exam);
        } else {
            exam.setCurrentQuestion(-1);
        }
        QuestionInfo questionInfo = new QuestionInfo();
        questionInfo.setExam(exam);
        questionInfo.setQuestion(currentQuestion);
        return questionInfo;
    }

    private static int getCurrentQuestionIndex(List<Question> questions, long questionId) {
        int index = 0;
        for (Question q : questions) {
            if (q.getId() == questionId) {
                break;
            }
            ++index;
        }
        return index;
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

    @Override
    public Exam selectById(long examId) {
        return examDAO.selectById(examId);
    }
}
