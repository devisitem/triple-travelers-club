package io.taech.triple.business.events.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.taech.triple.common.dto.StandardResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponse implements StandardResponse {

    @Valid
    @NotNull
    private ResultInfo result;

    @NotBlank
    private String userId;

    @NotNull
    private Integer totalMileages;
}
