package Service;

import java.util.List;

public interface ManageQuestion {

    public void deleteQuestion(Integer QuestionID);

    public void updateQuestion(Question question);

    public List listQuestions();

    public Integer addQuestion(Question question);

    Question getRandomQuestion();

    Question getQuestion(Integer QuestionID);

}
