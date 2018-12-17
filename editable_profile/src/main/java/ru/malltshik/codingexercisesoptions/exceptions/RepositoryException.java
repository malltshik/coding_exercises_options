package ru.malltshik.codingexercisesoptions.exceptions;

import org.springframework.dao.DataAccessException;

/**
 * Exception used for situation when {@link DataAccessException} has bean cached.
 * Common situations are data access exception or request not existed schema, table, column, etc.
 */
public class RepositoryException extends Exception {
    public RepositoryException(String message, DataAccessException e) {
        super(message, e);
    }
}
