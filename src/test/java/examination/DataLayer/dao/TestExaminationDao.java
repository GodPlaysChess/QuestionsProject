package examination.DataLayer.dao;

import examination.DataLayer.models.Exam;
import examination.DataLayer.models.Question;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestExaminationDao extends AbstractTest<Exam> {

    @Autowired
    private ExamDAO examDAO;
    @Autowired
    private QuestionDAO questionDAO;

    @Test
    public void test() {
        /* Insert Examination */
        Exam exam = new Exam();
        exam.setCourseId(1);
        exam.setStudentId(2);
        exam.setTimeStart(new Date());
        exam.setTimeFinish(new Date());
        exam.setCurrentQuestion(3);

        List<Question> questionList = new ArrayList<Question>();
        questionList.add(questionDAO.getRandomQuestion());
        questionList.add(questionDAO.getRandomQuestion());
        exam.setQuestions(questionList);

        baseCheck(exam, examDAO);
    }
}
