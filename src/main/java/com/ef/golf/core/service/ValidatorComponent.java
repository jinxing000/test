package com.ef.golf.core.service;

import com.ef.golf.core.exception.ValidatorException;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Component
public class ValidatorComponent {

    public void validate(Object obj) {
        Set<ConstraintViolation<Object>> validate = getValidator().validate(obj);
        if (validate.isEmpty()) {
            return;
        }
        throw new ValidatorException(validate);
    }

    private static Validator getValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator();
    }
}
