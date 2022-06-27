package io.taech.triple.business.events.dto.request;

import io.taech.triple.common.dto.StandardEventDto;
import io.taech.triple.common.dto.StandardRequest;
import lombok.Data;

@Data
public abstract class AbstractStandardEventDto implements StandardEventDto {

    private String type;
    private String action;

}
