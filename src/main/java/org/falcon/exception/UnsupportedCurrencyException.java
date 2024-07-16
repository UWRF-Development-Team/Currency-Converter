package org.falcon.exception;

public class UnsupportedCurrencyException extends RuntimeException {
    public static final String MESSAGE = "Error: Unsupported currency provided.";
    public UnsupportedCurrencyException() {
        super(MESSAGE);
    }
    public UnsupportedCurrencyException(String message) {
        super(message);
    }
}
