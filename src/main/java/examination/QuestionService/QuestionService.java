package examination.QuestionService;
import examination.DataLayer.models.Question;
import java.util.List;

public interface QuestionService {

    boolean addOrModifyQuestion(Question question);

    void deleteQuestion(Long questionID);

    Question getQuestion(Long questionID);

    List<Question> selectList(List<Long> questionIDs);


}
