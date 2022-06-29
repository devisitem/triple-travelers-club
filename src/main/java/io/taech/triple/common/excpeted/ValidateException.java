package io.taech.triple.common.excpeted;

public class ValidateException extends RuntimeException {

    private String message;
    private ServiceStatus status;

    public ValidateException(final ServiceStatus status) {
        super(status.getMessage());
        this.message = status.getMessage();
        this.status = status;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public ServiceStatus getStatus() {
        return this.status;
    }
}
