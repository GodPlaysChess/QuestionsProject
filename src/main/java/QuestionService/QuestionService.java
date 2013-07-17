package QuestionService;
import DataLayer.models.Question;
import java.util.List;

public interface QuestionService {

    boolean addQuestion(Question question);

    void deleteQuestion(Long QuestionID);

    void updateQuestion(Question question);

    List<Question> listQuestions();

    Question getRandomQuestion();

    Question getQuestion(Long QuestionID);

}
