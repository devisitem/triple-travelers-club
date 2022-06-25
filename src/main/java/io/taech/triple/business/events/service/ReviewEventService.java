package io.taech.triple.business.events.service;

import io.taech.triple.business.events.constant.ActionType;
import io.taech.triple.business.events.constant.EventType;
import io.taech.triple.business.events.dto.request.EventDto;
import io.taech.triple.common.excpeted.EventProcessingException;
import io.taech.triple.common.excpeted.ResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class ReviewEventService {

    private static EventType eventType = EventType.REVIEW;

    @Transactional
    public void consumeReviewEvent(final EventDto eventDto) throws EventProcessingException {
        final String action = eventDto.getAction();

        final ActionType actionType = eventType.getAction(action).orElseThrow(() -> {
            log.info("There is no action for \"{}\"", action);
            return new EventProcessingException(ResponseStatus.INVALID_EVENT);
        });

        log.info("Proceed event service for \"{}\" review.", action);

        switch(actionType) {
            case ADD:
                addReviewEventProcess(eventDto);
                break;
            case MOD:
                break;
            case DELETE:
                break;
            default:
                break;
        }

    }

    private void addReviewEventProcess(final EventDto eventDto) {

    }
}
