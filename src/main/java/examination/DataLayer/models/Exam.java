package examination.DataLayer.models;

import examination.DataLayer.models.enums.ExamStatus;

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
    private ExamStatus examStatus;

    public ExamStatus getExamStatus() {
        return examStatus;
    }

    public void setExamStatus(ExamStatus examStatus) {
        this.examStatus = examStatus;
    }

    public void setExamStatusCode(int examStatusCode) {
        examStatus = ExamStatus.getByValue(examStatusCode);
    }

    public int getExamStatusCode() {
        return examStatus.getCode();
    }


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
