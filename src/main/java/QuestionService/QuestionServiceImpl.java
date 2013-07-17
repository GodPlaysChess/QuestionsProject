package QuestionService;

import Service.dao.QuestionDAO;
import Service.models.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionDAO questionDAO;

    @Override
    public boolean addQuestion(Question question) {
        return questionDAO.addQuestion(question);
    }

    @Override
    public void deleteQuestion(Long QuestionID) {
        questionDAO.deleteQuestion(QuestionID);
    }

    @Override
    public void updateQuestion(Question question) {
        questionDAO.updateQuestion(question);
    }

    @Override
    public List<Question> listQuestions() {
        return questionDAO.listQuestions();
    }

    @Override
    public Question getRandomQuestion() {
        return questionDAO.getRandomQuestion();
    }

    @Override
    public Question getQuestion(Long QuestionID) {
        return questionDAO.getQuestion(QuestionID);
    }
}
