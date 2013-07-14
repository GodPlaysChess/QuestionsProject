package QuestionService;
import Service.Question;
import java.util.List;

public interface QuestionService {

    Long addQuestion(Question question);

    void deleteQuestion(Long QuestionID);

    void updateQuestion(Question question);

    List<Question> listQuestions();

    Question getRandomQuestion();

    Question getQuestion(Long QuestionID);

}
