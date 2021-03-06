package examination.DataLayer.models;

import examination.DataLayer.models.enums.AnswerStatus;
import examination.DataLayer.models.enums.Mark;

import java.util.Date;

public class Answer implements BaseModel {

    private long id;
    private long studentId;
    private long questionId;
    private long examId;
    private String text;
    private int intAnswer;
    private Mark mark;
    private AnswerStatus answerStatus;
    private Date timeStart;
    private Date timeFinish;

    public long getExamId() {
        return examId;
    }

    public void setExamId(long examId) {
        this.examId = examId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIntAnswer() {
        return intAnswer;
    }

    public void setIntAnswer(int intAnswer) {
        this.intAnswer = intAnswer;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    public AnswerStatus getAnswerStatus() {
        return answerStatus;
    }

    public void setAnswerStatus(AnswerStatus answerStatus) {
        this.answerStatus = answerStatus;
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

    public void setMarkCode(int markCode) {
        mark = Mark.getByValue(markCode);
    }

    public int getMarkCode() {
        return mark.getCode();
    }

    public void setAnswerStatusCode(int answerStatusCode) {
        answerStatus = AnswerStatus.getByValue(answerStatusCode);
    }

    public int getAnswerStatusCode() {
        return answerStatus.getCode();
    }

}
