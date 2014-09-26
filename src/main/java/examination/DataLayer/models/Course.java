package examination.DataLayer.models;

import java.util.List;

public class Course implements BaseModel {
    private long id;
    String name;
    private List<Question> questions;
    private int examQuestionsNumber;
    private GradingSystem gradingSystem;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GradingSystem getGradingSystem() {
        return gradingSystem;
    }

    public void setGradingSystem(GradingSystem gradingSystem) {
        this.gradingSystem = gradingSystem;
    }

    public int getExamQuestionsNumber() {
        return examQuestionsNumber;
    }

    public void setExamQuestionsNumber(int examQuestionsNumber) {
        this.examQuestionsNumber = examQuestionsNumber;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
