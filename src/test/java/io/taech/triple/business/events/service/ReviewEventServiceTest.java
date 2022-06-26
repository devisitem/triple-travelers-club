package io.taech.triple.business.events.service;

import io.taech.triple.business.events.dto.request.EventDto;
import io.taech.triple.common.base.MockingTester;
import io.taech.triple.common.excpeted.EventProcessingException;
import io.taech.triple.common.excpeted.ResponseStatus;
import io.taech.triple.common.excpeted.ValidateException;
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

    @Test
    @DisplayName("[리뷰 이벤트 처리] 올바르지 않는 UUID 형식 (리뷰 아이디 검증)")
    public void consumeReviewEvent2() throws Throwable {
        /* Given */
        final EventDto input = new EventDto();
        input.setType("REVIEW");
        input.setAction("ADD");
        input.setReviewId("240a06582dc5f-4878-9381-ebb7b2667772");

        /* When */
        final ValidateException actual = assertThrows(ValidateException.class, () -> service.consumeReviewEvent(input));

        /* Then */
        assertNotNull(actual);

    }

    @Test
    @DisplayName("[리뷰 이벤트 처리]")
    public void consumeReviewEvent3() throws Throwable {
        /* Given */

        /* When */

        /* Then */

    }
}