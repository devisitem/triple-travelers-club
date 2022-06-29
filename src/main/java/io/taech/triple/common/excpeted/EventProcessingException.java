package io.taech.triple.common.excpeted;

import lombok.Getter;

@Getter
public class EventProcessingException extends RuntimeException {

    private ServiceStatus serviceStatus;

    public EventProcessingException(final ServiceStatus serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    @Override
    public String getMessage() {
        return this.serviceStatus.getMessage();
    }
}
