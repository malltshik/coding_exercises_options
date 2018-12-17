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
import ru.malltshik.codingexercisesoptions.exceptions.ValidationModelException;
import ru.malltshik.codingexercisesoptions.exceptions.http.NotFoundException;

import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.*;

@ControllerAdvice
public class ControllerAdviceConfiguration {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> handleError(Exception e) {
        return new ResponseEntity<>(new ResponseError(e), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseError> handleError(NotFoundException e) {
        return new ResponseEntity<>(new ResponseError(e), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationModelException.class)
    public ResponseEntity<ResponseValidationError> handleError(ValidationModelException e) {
        return new ResponseEntity<>(new ResponseValidationError(e), HttpStatus.BAD_REQUEST);
    }

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
