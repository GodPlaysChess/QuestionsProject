package examination.validation;

import examination.DataLayer.models.Question;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * author: a.savanovich
 * Date: 14.08.13
 * Time: 9:30
 */
@Component
public class QuestionValidator implements Validator {
    @Override
    public boolean supports(Class<?> type) {
        return type.isAssignableFrom(Question.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "text", "text", "field.required");

        Question question = (Question) o;

        if (question.getId() < 0) {
            errors.rejectValue("id", "field.required");
        }
    }
}
