CREATE TABLE `questionsproject`.`courses_questions` (
  `course_id`   BIGINT NOT NULL,
  `question_id` BIGINT NOT NULL,
  PRIMARY KEY (`course_id`, `question_id`));


INSERT INTO courses_questions (course_id, question_id) VALUES (1, 1);
INSERT INTO courses_questions (course_id, question_id) VALUES (1, 28);
INSERT INTO courses_questions (course_id, question_id) VALUES (1, 29);
INSERT INTO courses_questions (course_id, question_id) VALUES (2, 1);
INSERT INTO courses_questions (course_id, question_id) VALUES (2, 30);
INSERT INTO courses_questions (course_id, question_id) VALUES (2, 31);
