package Service;

import java.util.List;

public interface ManageQuestion {

    public void deleteQuestion(Integer QuestionID);

    public void updateQuestion(Integer QuestionID, String newText);

    public List<Object> listQuestions();

    public Integer addQuestion(String type, String text);

}
