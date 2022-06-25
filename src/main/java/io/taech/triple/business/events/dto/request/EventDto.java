package io.taech.triple.business.events.dto.request;

import io.taech.triple.common.dto.StandardRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDto implements StandardRequest {

    @NotBlank
    private String type;

    @NotBlank
    private String action;

    @NotBlank
    private String reviewId;

    @NotBlank
    private String content;

    private List<String> attachedPhotoIds;

    @NotBlank
    private String userId;

    @NotBlank
    private String placeId;
}
