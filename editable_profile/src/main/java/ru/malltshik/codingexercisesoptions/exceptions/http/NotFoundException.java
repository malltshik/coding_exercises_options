package ru.malltshik.codingexercisesoptions.exceptions.http;

import ru.malltshik.codingexercisesoptions.configurations.ControllerAdviceConfiguration;

/**
 * Exception used for situations when resource not found.
 * <p>
 * Transfer to response here {@link ControllerAdviceConfiguration}
 */
public class NotFoundException extends Exception {
    public NotFoundException(String message) {
        super(message);
    }
}
