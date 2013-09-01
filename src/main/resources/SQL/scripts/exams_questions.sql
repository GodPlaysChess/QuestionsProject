CREATE TABLE `questionsproject`.exams_questions (
  `exam_id`     BIGINT NOT NULL AUTO_INCREMENT,
  `question_id` BIGINT NOT NULL,
  `index_col` INTEGER NOT NULL,
  PRIMARY KEY (`exam_id`, `question_id`));


