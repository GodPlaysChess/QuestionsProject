CREATE TABLE `questionsproject`.`exams` (
  `exam_id`          BIGINT      NULL AUTO_INCREMENT,
  `course_id`        BIGINT   NOT NULL,
  `student_id`       BIGINT   NOT NULL,
  `time_start`       DATETIME,
  `time_finish`      DATETIME,
  `current_question` BIGINT      NOT NULL,
  PRIMARY KEY (`exam_id`));

