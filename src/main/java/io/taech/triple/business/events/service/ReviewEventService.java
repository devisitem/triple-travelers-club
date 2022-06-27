package io.taech.triple.business.events.service;

import io.taech.triple.business.events.dto.request.EventDto;
import io.taech.triple.business.events.entity.TravelersReview;
import io.taech.triple.business.events.repository.support.ReviewRepositorySupport;
import io.taech.triple.common.dto.StandardEventDto;
import io.taech.triple.common.dto.StandardResponse;
import io.taech.triple.common.excpeted.EventProcessingException;
import io.taech.triple.common.excpeted.ResponseStatus;
import io.taech.triple.common.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static io.taech.triple.business.events.service.ReviewEventService.BEAN_NAME;

@Slf4j
@Service(BEAN_NAME)
@RequiredArgsConstructor
public class ReviewEventService implements EventService {

    public static final String BEAN_NAME = "reviewEventService";

    private final ReviewRepositorySupport repository;


    private TravelersReview getReviewAboutEvent(final EventDto eventDto) throws Throwable {
        final TravelersReview review = repository.findReview(eventDto.getReviewId(), eventDto.getUserId());

        if (Utils.isNull(review)) {
            log.error("There is no review data that which has id \"{}\" with user id \"{}\".", eventDto.getReviewId(), eventDto.getUserId());
            throw new EventProcessingException(ResponseStatus.NOT_FOUND_REVIEW_DATA);
        }

        log.info("found review data is {}", review);

        return review;
    }


    @Override
    @Transactional
    public StandardResponse proceedAddEvent(final StandardEventDto standardEvent) throws Throwable {
        //== check review ==//
        final EventDto event = (EventDto) standardEvent;
        final TravelersReview review = getReviewAboutEvent(event);



        return null;
    }

    @Override
    @Transactional
    public StandardResponse proceedModifyEvent(final StandardEventDto standardEvent) throws Throwable {

        return null;
    }

    @Override
    @Transactional
    public StandardResponse proceedDeleteEvent(final StandardEventDto standardEvent) throws Throwable {

        return null;
    }

}
