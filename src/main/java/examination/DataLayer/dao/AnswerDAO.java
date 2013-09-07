package examination.DataLayer.dao;

import examination.DataLayer.models.Answer;

import java.util.Date;

public interface AnswerDAO extends BaseDAO<Answer> {

    Answer getAnswerByQuestionId(long id, long prevQuestionId);
}
