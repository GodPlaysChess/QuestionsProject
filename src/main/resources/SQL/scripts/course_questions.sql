CREATE  TABLE `questionsproject`.`courses_questions` (
  `course_id` BIGINT NOT NULL ,
  `question_id` BIGINT NOT NULL ,
  PRIMARY KEY (`course_id`, `question_id`) );


INSERT into courses_questions (course_id, question_id) values (1, 1);
INSERT into courses_questions (course_id, question_id) values (1, 28);
INSERT into courses_questions (course_id, question_id) values (1, 29);
INSERT into courses_questions (course_id, question_id) values (2, 1);
INSERT into courses_questions (course_id, question_id) values (2, 30);
INSERT into courses_questions (course_id, question_id) values (2, 31);
