package Service;

import java.util.List;

public interface ManageQuestion extends BaseDAO {

    void deleteQuestion(Long QuestionID);

    void updateQuestion(Question question);

    List<Question> listQuestions();

    Long addQuestion(Question question);

    Question getRandomQuestion();

    Question getQuestion(Long QuestionID);

}
