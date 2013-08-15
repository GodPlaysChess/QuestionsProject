CREATE  TABLE `questionsproject`.`courses` (
  `course_id` BIGINT NOT NULL AUTO_INCREMENT ,
  `questions_number` INT NULL ,
  `grading_system` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`course_id`) );
