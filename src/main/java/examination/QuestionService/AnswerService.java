package examination.QuestionService;
import examination.DataLayer.models.Answer;

public interface AnswerService {

    boolean manualSave(Answer answer);

    Answer selectById(long id);

    boolean autoSave(Answer answer);

}
