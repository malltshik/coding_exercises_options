package ru.malltshik.codingexercisesoptions.exceptions.http;

import org.springframework.validation.Errors;
import ru.malltshik.codingexercisesoptions.configurations.ControllerAdviceConfiguration;

/**
 * Exception used for situations when were some model validation errors
 * <p>
 * Transfer to response here {@link ControllerAdviceConfiguration}
 */
public class ValidationModelException extends Exception {

    private Errors errors;

    public ValidationModelException(String message, Errors errors) {
        super(message);
        this.errors = errors;
    }

    public Errors getErrors() {
        return errors;
    }

    public void setErrors(Errors errors) {
        this.errors = errors;
    }
}
