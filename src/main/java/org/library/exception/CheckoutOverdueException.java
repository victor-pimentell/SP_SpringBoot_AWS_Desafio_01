package org.library.exception;

public class CheckoutOverdueException extends RuntimeException {
    public CheckoutOverdueException(String message) {
        super(message);
    }
}
