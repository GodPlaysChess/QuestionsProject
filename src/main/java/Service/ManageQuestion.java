package Service;

public interface ManageQuestion {

    public void deleteQuestion(Integer QuestionID);

    public void updateQuestion(Integer QuestionID, String newText);

    public void listQuestions();

    public Integer addQuestion(String type, String text);

}
