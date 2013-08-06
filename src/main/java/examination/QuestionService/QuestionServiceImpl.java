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
    public boolean addQuestion(Question question) {
        return questionDAO.insert(question);
    }

    @Override
    public void deleteQuestion(Long QuestionID) {
        questionDAO.delete(QuestionID);
    }

    @Override
    public void updateQuestion(Question question) {
        questionDAO.update(question);
    }

    @Override
    public Question getQuestion(Long QuestionID) {
        return questionDAO.selectById(QuestionID);
    }



}
