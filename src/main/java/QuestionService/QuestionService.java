package QuestionService;
import Service.Question;
import java.util.List;

public interface QuestionService {

    public Integer addQuestion(Question question);

    public void deleteQuestion(Integer QuestionID);

    public void updateQuestion(Question question);

    public List listQuestions();

    Question getRandomQuestion();

    Question getQuestion(Integer QuestionID);

}
