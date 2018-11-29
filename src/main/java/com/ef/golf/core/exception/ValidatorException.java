package com.ef.golf.core.exception;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class ValidatorException extends IllegalArgumentException {

    private Set<ConstraintViolation<Object>> violations;

    public ValidatorException(Set<ConstraintViolation<Object>> violations) {
        this.violations = violations;
    }

    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder();
        for (ConstraintViolation<Object> violation : violations) {
            sb.append(","+violation.getMessage());
        }
        return sb.toString().substring(1,sb.length());
    }
}
