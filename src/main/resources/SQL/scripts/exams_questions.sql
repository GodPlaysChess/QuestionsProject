CREATE TABLE `questionsproject`.exams_questions (
  `exam_id`     BIGINT NOT NULL AUTO_INCREMENT,
  `question_id` BIGINT NOT NULL,
  PRIMARY KEY (`exam_id`, `question_id`));

INSERT INTO `exams_questions` (exam_id, question_id) VALUES (1, 1);
INSERT INTO `exams_questions` (exam_id, question_id) VALUES (1, 2);
INSERT INTO `exams_questions` (exam_id, question_id) VALUES (1, 5);
INSERT INTO `exams_questions` (exam_id, question_id) VALUES (1, 16);
INSERT INTO `exams_questions` (exam_id, question_id) VALUES (1, 9);
INSERT INTO `exams_questions` (exam_id, question_id) VALUES (2, 1);
INSERT INTO `exams_questions` (exam_id, question_id) VALUES (2, 11);
INSERT INTO `exams_questions` (exam_id, question_id) VALUES (3, 1);