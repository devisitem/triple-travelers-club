package io.taech.triple.common.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.taech.triple.business.events.dto.response.ResultInfo;
import io.taech.triple.common.excpeted.ServiceStatus;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
public class ErrorResponse implements StandardResponse {

    @Valid
    @NotNull
    private ResultInfo result;

    private ErrorResponse(final ResultInfo result) {
        this.result = result;
    }

    public static ErrorResponse create(final ServiceStatus status) {
        return new ErrorResponse(new ResultInfo(status.getCode(), status.getMessage()));
    }

    @Override
    public ResultInfo result() {
        return this.result;
    }
}
