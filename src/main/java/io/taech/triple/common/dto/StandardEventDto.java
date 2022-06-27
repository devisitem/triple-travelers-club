package io.taech.triple.common.dto;

import io.taech.triple.business.events.dto.request.EventDto;

public interface StandardEventDto {

    String getType();

    String getAction();
}
