package ru.malltshik.codingexercisesoptions.configurations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.malltshik.codingexercisesoptions.exceptions.http.NotFoundException;

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

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class ResponseError {
        private String message;

        ResponseError(Exception e) {
            this.message = e.getMessage();
        }
    }

}
