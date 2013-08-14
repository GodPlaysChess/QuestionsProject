package examination.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

public class ModelValidator implements Validator {

    private List<Validator> validators;

    @Autowired
    public void setValidators(List<Validator> validators) {
        this.validators = validators;
    }

    public boolean supports(Class<?> type) {

        for (Validator validator : validators) {
            if (validator.supports(type)) {
                return true;
            }
        }

        return false;
    }

    public void validate(Object o, Errors errors) {
        for (Validator validator : validators) {
            if (validator.supports(o.getClass())) {
                validator.validate(o, errors);
            }
        }
    }
}
