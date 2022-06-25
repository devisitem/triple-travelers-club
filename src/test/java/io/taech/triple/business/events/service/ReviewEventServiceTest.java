package io.taech.triple.business.events.service;

import io.taech.triple.business.events.dto.request.EventDto;
import io.taech.triple.common.base.MockingTester;
import io.taech.triple.common.excpeted.EventProcessingException;
import io.taech.triple.common.excpeted.ResponseStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.*;


class ReviewEventServiceTest extends MockingTester {

    @InjectMocks
    private ReviewEventService service;

    @Test
    @DisplayName("[리뷰 이벤트 처리] 존재하지않는 액션 타입")
    public void consumeReviewEvent1() throws Throwable {
        /* Given */
        final String action = "EDIT";
        final EventDto input = new EventDto();
        input.setAction(action);

        /* When */
        final EventProcessingException actual = assertThrows(EventProcessingException.class, () -> service.consumeReviewEvent(input));

        /* Then */
        assertEquals(ResponseStatus.INVALID_EVENT, actual.getResponseStatus());

    }
}