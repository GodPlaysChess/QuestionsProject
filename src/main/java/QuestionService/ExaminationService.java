package QuestionService;

import Service.Question;

/**
 * author: a.savanovich
 * Date: 15.07.13
 * Time: 16:05
 * To change this template use File | Settings | File Templates.
 */
public interface ExaminationService {
    Question start(long studentId, long courseId);

    Question next(long examenId);

    void finish(long examenId);
}
