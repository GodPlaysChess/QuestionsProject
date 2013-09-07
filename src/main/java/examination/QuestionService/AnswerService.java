package examination.QuestionService;
import examination.DataLayer.models.Answer;

public interface AnswerService {

    boolean save(Answer answer);

    Answer selectById(long id);

}
