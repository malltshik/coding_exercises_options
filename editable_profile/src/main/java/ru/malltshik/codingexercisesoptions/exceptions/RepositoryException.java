package ru.malltshik.codingexercisesoptions.exceptions;

import org.springframework.dao.DataAccessException;

public class RepositoryException extends Exception {
    public RepositoryException(String message, DataAccessException e) {
        super(message, e);
    }
}
