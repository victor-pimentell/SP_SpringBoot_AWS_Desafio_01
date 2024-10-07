package org.library.exception;

public class MaxNumberBookBorrowedException extends RuntimeException {
    public MaxNumberBookBorrowedException(String message) {
        super(message);
    }
}
