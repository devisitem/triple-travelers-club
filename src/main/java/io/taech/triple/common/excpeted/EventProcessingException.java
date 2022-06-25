package io.taech.triple.common.excpeted;

import lombok.Getter;

@Getter
public class EventProcessingException extends RuntimeException {

    private ResponseStatus responseStatus;

    public EventProcessingException(final ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }
}
