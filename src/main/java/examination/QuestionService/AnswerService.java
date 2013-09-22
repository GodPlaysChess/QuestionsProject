package examination.QuestionService;
import examination.DataLayer.models.Answer;

import java.util.List;

public interface AnswerService {

    boolean manualSave(Answer answer);

    Answer selectById(long id);

    boolean autoSave(Answer answer);

    List<Answer> getInevaluatedAnswers();

    List<Answer> getAnswersByExamId(long examId);

}
