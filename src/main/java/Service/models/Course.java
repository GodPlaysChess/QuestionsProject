package Service.models;

import java.util.List;

/**
 * author: a.savanovich
 * Date: 14.07.13
 * Time: 15:12
 */
public class Course implements BaseModel {
    private long id;
    List<Long> questionsIds;

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
