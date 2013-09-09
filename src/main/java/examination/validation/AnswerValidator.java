package examination.validation;

import examination.DataLayer.models.Answer;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AnswerValidator implements Validator {
    @Override
    public boolean supports(Class<?> type) {
        return type.isAssignableFrom(Answer.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "text", "text", "field.required");
        Answer answer = (Answer) o;

        if (answer.getId() < 0) {
            errors.rejectValue("id", "field.required");
        }
        if (answer.getExamId() < 0) {
            errors.rejectValue("examId", "field.required");
        }
        if (answer.getStudentId() < 0) {
            errors.rejectValue("studentId", "field.required");
        }
        if (answer.getQuestionId() < 0) {
            errors.rejectValue("questionId", "field.required");
        }
    }
}
