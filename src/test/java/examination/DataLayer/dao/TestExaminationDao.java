package examination.DataLayer.dao;

import examination.DataLayer.models.Exam;
import examination.DataLayer.models.Question;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
        questionList.add(questionDAO.getRandomQuestion());
        exam.setQuestions(questionList);

        boolean inserted = examDAO.insert(exam);
        assertTrue(exam.getId() > 0);
        assertTrue(inserted);

        long id = exam.getId();
        Exam exam1 = examDAO.selectById(id);
        assertNotNull(exam1);

        List<Question> newList = exam1.getQuestions();
        for (int i = 0; i < questionList.size(); ++i) {
            assertEquals(questionList.get(i).getId(), newList.get(i).getId());
        }


        examDAO.delete(id);
        assertNull(examDAO.selectById(id));

    }
}
