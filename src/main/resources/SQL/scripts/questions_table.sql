CREATE TABLE `questionsproject`.`questions` (
  `question_id` BIGINT      NOT NULL AUTO_INCREMENT,
  `type`        INT         NOT NULL,
  `text`        VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));


INSERT INTO questions (id, type, text) VALUES (1, 1, '5*2')
