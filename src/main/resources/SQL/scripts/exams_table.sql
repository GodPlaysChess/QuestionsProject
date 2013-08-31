CREATE TABLE `questionsproject`.`exams` (
  `exam_id`          BIGINT      NULL AUTO_INCREMENT,
  `course_id`        VARCHAR(45) NOT NULL,
  `student_id`       VARCHAR(45) NOT NULL,
  `time_start`       DATETIME,
  `time_finish`      DATETIME,
  `current_question` BIGINT      NOT NULL,
  PRIMARY KEY (`exam_id`));

