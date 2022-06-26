package io.taech.triple.business.events.service;

import io.taech.triple.business.events.constant.ActionType;
import io.taech.triple.business.events.constant.EventType;
import io.taech.triple.business.events.dto.request.EventDto;
import io.taech.triple.business.events.dto.response.StandardResponse;
import io.taech.triple.common.excpeted.EventProcessingException;
import io.taech.triple.common.excpeted.ResponseStatus;
import io.taech.triple.common.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventService {

    private final ReviewEventService reviewEventService;

    public StandardResponse consumeEvent(final EventDto eventDto) throws Exception {
        final String typeName = eventDto.getType();
        final EventType foundType = EventType.find(typeName);

        if(Utils.isNull(foundType)) {
            log.error("Invalid event type. there is no type for event which name like \"{}\"", typeName);
            throw new EventProcessingException(ResponseStatus.INVALID_EVENT);
        }

        switch (foundType) {
            case REVIEW:
                reviewEventService.consumeReviewEvent(eventDto);
                break;
        }

        return null;
    }
}

