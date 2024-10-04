package org.library.exception;

public class AuthorAlreadyRegisteredException extends RuntimeException {
    public AuthorAlreadyRegisteredException(String message) {
        super(message);
    }
}
