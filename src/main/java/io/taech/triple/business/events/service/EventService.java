package io.taech.triple.business.events.service;

import io.taech.triple.common.dto.StandardEventDto;
import io.taech.triple.common.dto.response.StandardResponse;

public interface EventService {

        StandardResponse proceedAddEvent(final StandardEventDto standardEvent) throws Throwable;

        StandardResponse proceedModifyEvent(final StandardEventDto standardEvent) throws Throwable;

        StandardResponse proceedDeleteEvent(final StandardEventDto standardEvent) throws Throwable;

}

