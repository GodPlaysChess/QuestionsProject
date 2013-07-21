package examination.DataLayer.dao;

import examination.DataLayer.models.Question;

import java.util.List;

public interface QuestionDAO extends BaseDAO<Question> {

    Question getRandomQuestion();

    List<Question> selectList(List<Long> questionIds);
}
