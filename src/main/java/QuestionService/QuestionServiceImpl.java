package QuestionService;
import Service.ManageQuestion;
import org.springframework.beans.factory.annotation.Autowired;

public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private ManageQuestion manageQuestion;
}
