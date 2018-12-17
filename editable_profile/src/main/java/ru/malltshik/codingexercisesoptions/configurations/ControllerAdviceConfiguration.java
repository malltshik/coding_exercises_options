package ru.malltshik.codingexercisesoptions.configurations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.malltshik.codingexercisesoptions.exceptions.http.ValidationModelException;
import ru.malltshik.codingexercisesoptions.exceptions.http.NotFoundException;

import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * This is classic way intercept java exceptions and transfer its to response model (future JSON object)
 */
@ControllerAdvice
public class ControllerAdviceConfiguration {


    /**
     * All {@link Exception} returns response with {@link HttpStatus#INTERNAL_SERVER_ERROR} status
     * @param e exception
     * @return {@link ResponseError} parametrized with {@link ResponseError}
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> handleError(Exception e) {
        return new ResponseEntity<>(new ResponseError(e), INTERNAL_SERVER_ERROR);
    }

    /**
     * All {@link NotFoundException} returns response with {@link HttpStatus#NOT_FOUND} status
     * @param e exception
     * @return {@link ResponseError} parametrized with {@link ResponseError}
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseError> handleError(NotFoundException e) {
        return new ResponseEntity<>(new ResponseError(e), NOT_FOUND);
    }

    /**
     * All {@link ValidationModelException} returns response with {@link HttpStatus#BAD_REQUEST} status
     * @param e exception
     * @return {@link ResponseValidationError} parametrized with {@link ResponseValidationError}
     */
    @ExceptionHandler(ValidationModelException.class)
    public ResponseEntity<ResponseValidationError> handleError(ValidationModelException e) {
        return new ResponseEntity<>(new ResponseValidationError(e), BAD_REQUEST);
    }

    /**
     * Data transfer object to pretty JSON response
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class ResponseError {
        protected String message;
        protected String type;
        ResponseError(Exception e) {
            this.type = e.getClass().getSimpleName();
            this.message = e.getMessage();
        }

    }

    /**
     * Data transfer object to pretty JSON response
     */
    @Data
    @EqualsAndHashCode(callSuper = true)
    class ResponseValidationError extends ResponseError {
        private Map<String, Set<String>> errors;

        ResponseValidationError(ValidationModelException e) {
            super(e);
            this.errors = e.getErrors().getFieldErrors().stream()
                    .collect(groupingBy(FieldError::getField, mapping(FieldError::getDefaultMessage, toSet())));
        }
    }

}
