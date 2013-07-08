package QuestionService;

import Service.ManageQuestion;
import Service.Question;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private ManageQuestion manageQuestion;

    @Override
    public Integer addQuestion(Question question) {
        return manageQuestion.addQuestion(question);
    }

    @Override
    public void deleteQuestion(Integer QuestionID) {
        manageQuestion.deleteQuestion(QuestionID);
    }

    @Override
    public void updateQuestion(Question question) {
        manageQuestion.updateQuestion(question);
    }

    @Override
    public List<Object> listQuestions() {
        return manageQuestion.listQuestions();
    }

    @Override
    public Question getRandomQuestion() {
        return manageQuestion.getRandomQuestion();
    }

    @Override
    public Question getQuestion(Integer QuestionID) {
        return manageQuestion.getQuestion(QuestionID);
    }
}
