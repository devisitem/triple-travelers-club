package io.taech.triple.business.events.service;


import io.taech.triple.business.events.dto.request.EventDto;
import io.taech.triple.common.base.MockingTester;
import io.taech.triple.common.excpeted.EventProcessingException;
import io.taech.triple.common.excpeted.ResponseStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.*;

class EventServiceTest extends MockingTester {

    @InjectMocks
    private EventService service;

    @Test
    @DisplayName("[이벤트 처리] 존재하지 않는 타입")
    public void consumeEvent1() throws Throwable {
        /* Given */
        final String eventType = "PROMOTION";
        final EventDto input = new EventDto();
        input.setType(eventType);

        /* When */
        final EventProcessingException actual = assertThrows(EventProcessingException.class, () -> service.consumeEvent(input));

        /* Then */
        assertEquals(ResponseStatus.INVALID_EVENT, actual.getResponseStatus());

    }
}