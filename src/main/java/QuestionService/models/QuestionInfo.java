package QuestionService.models;

import Service.models.Question;
import Service.models.Exam;

/**
 * author: a.savanovich
 * Date: 15.07.13
 * Time: 20:10
 * To change this template use File | Settings | File Templates.
 */
public class QuestionInfo {
    private Question question;
    private Exam exam;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
}
