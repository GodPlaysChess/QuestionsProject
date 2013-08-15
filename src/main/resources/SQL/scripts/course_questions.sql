CREATE  TABLE `questionsproject`.`courses_questions` (
  `course_id` BIGINT NOT NULL ,
  `question_id` BIGINT NOT NULL ,
  PRIMARY KEY (`course_id`, `question_id`) );
