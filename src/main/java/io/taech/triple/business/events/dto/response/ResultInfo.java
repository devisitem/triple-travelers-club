package io.taech.triple.business.events.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.taech.triple.common.excpeted.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultInfo {

    @NotNull
    private Integer code;

    @JsonInclude(Include.NON_NULL)
    private String message;

    public static ResultInfo success() {
        final ResultInfo result = new ResultInfo();
        result.code = ResponseStatus.SUCCESS.getCode();

        return result;
    }
}
