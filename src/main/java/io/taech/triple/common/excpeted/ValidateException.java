package io.taech.triple.common.excpeted;

public class ValidateException extends RuntimeException {

    private String message;

    public ValidateException(final String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
