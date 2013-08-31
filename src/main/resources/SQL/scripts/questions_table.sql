CREATE TABLE `questionsproject`.`questions` (
  `question_id` BIGINT      NOT NULL AUTO_INCREMENT,
  `type`        INT         NOT NULL,
  `text`        VARCHAR(45) NOT NULL,
  PRIMARY KEY (`question_id`));


INSERT INTO questions (question_id, type, text) VALUES (1, 1, '5*2');
INSERT INTO questions (question_id, type, text) VALUES (28, 1, 'still one left');
INSERT INTO questions (question_id, type, text) VALUES (29, 1, 'the last one');
