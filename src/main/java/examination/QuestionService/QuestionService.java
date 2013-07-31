package examination.QuestionService;
import examination.DataLayer.models.Question;
import java.util.List;

public interface QuestionService {

    boolean addQuestion(Question question);

    void deleteQuestion(Long QuestionID);

    void updateQuestion(Question question);

    Question getQuestion(Long QuestionID);

    List<Question> listQuestions();

}
