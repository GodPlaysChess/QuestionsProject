package QuestionService;

import QuestionService.models.QuestionInfo;
import Service.models.Exam;

import java.util.List;

/**
 * author: a.savanovich
 * Date: 15.07.13
 * Time: 16:05
 * To change this template use File | Settings | File Templates.
 */
public interface ExaminationService {
    QuestionInfo start(long studentId, long courseId);

    QuestionInfo next(long examenId);

    QuestionInfo current(long examenId);

    void finish(long examenId);

    List<Exam> getCurrentExams(long studentId);
}
