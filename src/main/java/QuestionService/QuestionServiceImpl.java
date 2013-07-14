package QuestionService;

import Service.ManageQuestion;
import Service.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private ManageQuestion manageQuestion;

    @Override
    public Long addQuestion(Question question) {
        return manageQuestion.addQuestion(question);
    }

    @Override
    public void deleteQuestion(Long QuestionID) {
        manageQuestion.deleteQuestion(QuestionID);
    }

    @Override
    public void updateQuestion(Question question) {
        manageQuestion.updateQuestion(question);
    }

    @Override
    public List<Question> listQuestions() {
        return manageQuestion.listQuestions();
    }

    @Override
    public Question getRandomQuestion() {
        return manageQuestion.getRandomQuestion();
    }

    @Override
    public Question getQuestion(Long QuestionID) {
        return manageQuestion.getQuestion(QuestionID);
    }
}
