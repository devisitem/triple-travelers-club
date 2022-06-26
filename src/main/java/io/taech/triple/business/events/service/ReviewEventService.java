package io.taech.triple.business.events.service;

import io.taech.triple.business.events.constant.ActionType;
import io.taech.triple.business.events.constant.EventType;
import io.taech.triple.business.events.dto.request.EventDto;
import io.taech.triple.business.events.entity.TravelersReview;
import io.taech.triple.business.events.repository.TravelersReviewRepository;
import io.taech.triple.common.excpeted.EventProcessingException;
import io.taech.triple.common.excpeted.ResponseStatus;
import io.taech.triple.common.util.Utils;
import io.taech.triple.common.util.ValidUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewEventService {

    private static EventType eventType = EventType.REVIEW;
    private final TravelersReviewRepository travelersReviewRepository;

    @Transactional
    public void consumeReviewEvent(final EventDto eventDto) throws Exception {
        final String action = eventDto.getAction();

        final ActionType actionType = eventType.getAction(action).orElseThrow(() -> {
            log.info("There is no action for \"{}\"", action);
            return new EventProcessingException(ResponseStatus.INVALID_EVENT);
        });

        log.info("Proceed event service for \"{}\" review.", action);

        final TravelersReview review = getReviewWithInspectEvent(eventDto);

        switch(actionType) {
            case ADD:
                addReviewEventProcess(eventDto, review);
                break;
            case MOD:
                break;
            case DELETE:
                break;
            default:
                break;
        }

    }

    private TravelersReview getReviewWithInspectEvent(final EventDto eventDto) throws Exception {

        final UUID reviewId = ValidUtils.getWithInspect(eventDto.getReviewId());
        final UUID userId = ValidUtils.getWithInspect(eventDto.getUserId());
        final TravelersReview review = travelersReviewRepository.findReview(reviewId, userId);

        if (Utils.isNull(review)) {
            log.error("There is no review data that which has id \"{}\" with user id \"{}\".", reviewId, userId);
            throw new EventProcessingException(ResponseStatus.NOT_FOUND_REVIEW_DATA);
        }

        log.info("found review data is {}", review);

        return review;
    }

    private void addReviewEventProcess(final EventDto eventDto, final TravelersReview review) throws Exception {

        final UUID placeId = review.getPlaceId();
        log.info("place id is \"{}\"", placeId);
    }
}
