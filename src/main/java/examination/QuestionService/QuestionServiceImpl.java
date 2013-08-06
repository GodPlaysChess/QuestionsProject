package examination.QuestionService;

import examination.DataLayer.dao.QuestionDAO;
import examination.DataLayer.models.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionDAO questionDAO;

    @Override
    public boolean addOrModifyQuestion(Question question) {
        if (question.getId() > 0) {
            return questionDAO.update(question);
        }
        return questionDAO.insert(question);
    }

    @Override
    public void deleteQuestion(Long QuestionID) {
        questionDAO.delete(QuestionID);
    }

    @Override
    public Question getQuestion(Long QuestionID) {
        Question q = questionDAO.selectById(QuestionID);
        if (q==null) {
            return new Question();
        }
        return q;
    }



}
