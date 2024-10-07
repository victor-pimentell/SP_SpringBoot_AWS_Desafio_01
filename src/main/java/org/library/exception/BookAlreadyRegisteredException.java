package org.library.exception;

public class BookAlreadyRegisteredException extends RuntimeException {
    public BookAlreadyRegisteredException(String message) {
        super(message);
    }
}
