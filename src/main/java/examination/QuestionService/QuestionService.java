package examination.QuestionService;
import examination.DataLayer.models.Question;
import java.util.List;

public interface QuestionService {

    boolean addOrModifyQuestion(Question question);

    void deleteQuestion(Long QuestionID);

    Question getQuestion(Long QuestionID);

    List<Question> listQuestions();

}
