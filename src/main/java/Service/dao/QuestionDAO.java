package Service.dao;

import Service.dao.BaseDAO;
import Service.models.Question;

import java.util.List;

public interface QuestionDAO extends BaseDAO {

    boolean deleteQuestion(Long QuestionID);

    boolean updateQuestion(Question question);

    List<Question> listQuestions();

    List<Question> selectList(List<Long> questionIds);

    boolean addQuestion(Question question);

    Question getRandomQuestion();

    Question getQuestion(Long QuestionID);

}
