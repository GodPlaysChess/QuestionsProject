package examination.DataLayer.models;

import java.util.List;

/**
 * author: a.savanovich
 * Date: 14.07.13
 * Time: 15:12
 */
public class Course implements BaseModel {
    private long id;
    private List<Long> questionsIds;
    private int examQuestionsNumber;
    private GradingSystem gradingSystem;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Long> getQuestionsIds() {
        return questionsIds;
    }

    public void setQuestionsIds(List<Long> questionsIds) {
        this.questionsIds = questionsIds;
    }
}
