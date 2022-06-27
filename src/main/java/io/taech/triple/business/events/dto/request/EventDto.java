package io.taech.triple.business.events.dto.request;

import io.taech.triple.common.dto.StandardRequest;
import io.taech.triple.common.util.ValidUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDto extends AbstractStandardEventDto {

    @NotNull
    private UUID reviewId;

    @NotBlank
    private String content;

    private List<String> attachedPhotoIds;

    @NotNull
    private UUID userId;

    @NotNull
    private UUID placeId;

    public void setReviewId(final String reviewId) {
        this.reviewId = ValidUtils.getWithInspect(reviewId);
    }

    public void setUserId(final String userId) {
        this.userId = ValidUtils.getWithInspect(userId);
    }

    public void setPlaceId(final String placeId) {
        this.placeId = ValidUtils.getWithInspect(placeId);
    }

}
