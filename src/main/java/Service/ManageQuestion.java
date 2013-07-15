package Service;

import Service.dao.BaseDAO;

import java.util.List;

public interface ManageQuestion extends BaseDAO {

    void deleteQuestion(Long QuestionID);

    void updateQuestion(Question question);

    List<Question> listQuestions();

    List<Question> selectList(List<Long> questionIds);

    Long addQuestion(Question question);

    Question getRandomQuestion();

    Question getQuestion(Long QuestionID);

}
