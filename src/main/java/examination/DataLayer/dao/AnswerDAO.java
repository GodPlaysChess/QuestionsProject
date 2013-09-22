package examination.DataLayer.dao;

import examination.DataLayer.models.Answer;

import java.util.Date;
import java.util.List;

public interface AnswerDAO extends BaseDAO<Answer> {

    Answer getAnswerByQuestionId(long id, long prevQuestionId);

    List<Answer> getInevaluatedAnswers();

    List<Answer> getAnswerByExamId(long examId);
}
