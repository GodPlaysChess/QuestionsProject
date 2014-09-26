package examination.DataLayer.dao;

import examination.DataLayer.models.Exam;
import examination.DataLayer.models.Question;
import examination.DataLayer.models.enums.ExamStatus;
import org.junit.Assert;
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
        //exam.setId(0);
        exam.setCourseId(1);
        exam.setStudentId(2);
        exam.setTimeStart(new Date());
        exam.setTimeFinish(new Date());
        exam.setCurrentQuestion(3);
        exam.setExamStatus(ExamStatus.NOT_CHECKED);

        List<Question> questionList = new ArrayList<Question>();
        questionList.add(questionDAO.selectById(1));
        questionList.add(questionDAO.selectById(29));
        questionList.add(questionDAO.selectById(28));
        exam.setQuestions(questionList);

        /* insert */
        boolean inserted = examDAO.insert(exam);
        assertTrue(exam.getId() > 0);
        assertTrue(inserted);

        /* select */
        long id = exam.getId();
        Exam exam1 = examDAO.selectById(id);
        assertNotNull(exam1);

        /* ordering test */
        List<Question> newList = exam1.getQuestions();
        for (int i = 0; i < questionList.size(); ++i) {
            assertEquals(questionList.get(i).getId(), newList.get(i).getId());
        }

        /* get by student id */
        List<Exam> exams = examDAO.getCurrentExams(2);
        Assert.assertNotNull(exams);

        /* update */
        exam.setCourseId(4);
        examDAO.update(exam);
        exam1 = examDAO.selectById(id);
        assertEquals(exam1.getCourseId(), 4);

        /* delete */
        examDAO.delete(id);
        assertNull(examDAO.selectById(id));

        /* not entirely corrected yet */
        exams = examDAO.getInevaluatedExams(0);
        assertNotNull(exams);
        if (exams.size() > 0) {
            for (Exam e : exams) {
                assertEquals(e.getExamStatus(), ExamStatus.NOT_CHECKED);
            }
        }


    }
}
