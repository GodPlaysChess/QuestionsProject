package examination.QuestionService;

import examination.QuestionService.models.QuestionInfo;
import examination.DataLayer.models.Exam;

import javax.annotation.Nullable;
import java.util.List;

public interface ExaminationService {
    QuestionInfo start(long studentId, long courseId);

    QuestionInfo next(long examenId);

    QuestionInfo current(long examenId);

    List<Exam> getCurrentExams(long studentId);

    @Nullable
    Exam selectById(long examId);
}
