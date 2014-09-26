CREATE TABLE `questionsproject`.`courses` (
  `course_id`        BIGINT      NOT NULL AUTO_INCREMENT,
  `name` TEXT,
  `questions_number` INT,
  `grading_system`   INT,
  PRIMARY KEY (`course_id`));

INSERT INTO questionsproject.courses (course_id, name, questions_number, grading_system) VALUES (1, 'Философия', 3, 0);





