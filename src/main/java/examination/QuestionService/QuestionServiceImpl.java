package examination.QuestionService;

import examination.DataLayer.dao.QuestionDAO;
import examination.DataLayer.models.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
    public void deleteQuestion(Long questionID) {
        questionDAO.delete(questionID);
    }

    @Override
    public Question getQuestion(Long questionID) {
        Question q = questionDAO.selectById(questionID);
        if (q == null) {
            return new Question();
        }
        return q;
    }

    @Override
    public List<Question> selectList(List<Long> questionIDs) {
        if (questionIDs == null || questionIDs.isEmpty()) {
            return Collections.emptyList();
        }
        return questionDAO.selectList(questionIDs);
    }


}
