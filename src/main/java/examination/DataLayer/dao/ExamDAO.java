package examination.DataLayer.dao;

import examination.DataLayer.models.Exam;

import java.util.List;

public interface ExamDAO extends BaseDAO<Exam> {
    List<Exam> getCurrentExams(long studentId);

    List<Exam> getInevaluatedExams(long courseId);
}
