CREATE TABLE `questionsproject`.`examCorrelation` (
  `exam_id`     BIGINT NOT NULL AUTO_INCREMENT,
  `question_id` BIGINT NOT NULL,
  PRIMARY KEY (`exam_id`, `question_id`));

INSERT INTO examcorrelation (exam_id, question_id) VALUES (1, 1);
INSERT INTO examcorrelation (exam_id, question_id) VALUES (1, 2);
INSERT INTO examcorrelation (exam_id, question_id) VALUES (1, 5);
INSERT INTO examcorrelation (exam_id, question_id) VALUES (1, 16);
INSERT INTO examcorrelation (exam_id, question_id) VALUES (1, 9);
INSERT INTO examcorrelation (exam_id, question_id) VALUES (2, 1);
INSERT INTO examcorrelation (exam_id, question_id) VALUES (2, 11);
INSERT INTO examcorrelation (exam_id, question_id) VALUES (3, 1);