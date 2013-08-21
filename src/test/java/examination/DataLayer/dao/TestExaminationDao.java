package examination.DataLayer.dao;

import examination.DataLayer.models.Exam;
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


import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/spring/applicationContext.xml"})
public class TestExaminationDao {

    @Autowired
    private ExamDAO examDAO;
    @Autowired
    private QuestionDAO questionDAO;
    private static final Logger log = Logger.getLogger(TestExaminationDao.class);


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

        boolean inserted = examDAO.insert(exam);
        assertTrue(exam.getId() > 0);
        assertTrue(inserted);

        long id = exam.getId();

        /* get exam */
        exam = examDAO.selectById(id);
        assertNotNull(exam);

        /* delete exam */
        examDAO.delete(id);
        assertNull(examDAO.selectById(id));

        log.info(examDAO);
    }
}
