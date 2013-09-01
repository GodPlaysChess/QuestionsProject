CREATE TABLE `questionsproject`.`answers` (
  `answer_id` BIGINT NOT NULL AUTO_INCREMENT,
  `student_id` BIGINT NOT NULL,
  `question_id` BIGINT NOT NULL,
  `exam_id` BIGINT NOT NULL,
  `text` VARCHAR(45) NULL,
  `int_answer` INT NULL,
  `mark` INT NULL,
  `answer_status` INT NULL,
  `time_start` DATETIME NULL,
  `time_finish` DATETIME NULL,
  PRIMARY KEY (`answer_id`));
