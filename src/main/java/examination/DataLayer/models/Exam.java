package examination.DataLayer.models;

import java.util.Date;
import java.util.List;

public class Exam implements BaseModel {
    private long id;
    private long courseId;
    private long studentId;
    private List<Question> questions;
    private Date timeStart;
    private Date timeFinish;
    private long currentQuestion;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Date getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public Date getTimeFinish() {
        return timeFinish;
    }

    public void setTimeFinish(Date timeFinish) {
        this.timeFinish = timeFinish;
    }

    public long getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(long currentQuestion) {
        this.currentQuestion = currentQuestion;
    }
}
