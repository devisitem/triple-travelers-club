package io.taech.triple.business.events.service;


import io.taech.triple.business.events.dto.request.EventDto;
import io.taech.triple.business.events.repository.support.ReviewRepositorySupport;
import io.taech.triple.common.base.MockingTester;
import io.taech.triple.common.excpeted.EventProcessingException;
import io.taech.triple.common.excpeted.ResponseStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class ReviewEventServiceTest extends MockingTester {

    @InjectMocks
    private ReviewEventService service;

    @Mock
    private ReviewRepositorySupport repository;

    @Test
    @DisplayName("[리뷰 작성 이벤트 처리] 존재하지 않는 리뷰")
    public void getReviewAboutEvent1() throws Throwable {
        /* Given */
        final String reviewId = "1bbff29d-69db-404e-b6fb-396de18ebb85";
        final String userId = "7c4f1e5f-22f1-4028-a2e7-5395391b22c5";
        final EventDto input = new EventDto();
        input.setReviewId(reviewId);
        input.setUserId(userId);

        /* When */
        when(repository.findReview(input.getReviewId(), input.getUserId()))
                .thenReturn(null);
        final EventProcessingException actual = assertThrows(EventProcessingException.class, () -> service.proceedAddEvent(input));

        /* Then */
        assertEquals(ResponseStatus.NOT_FOUND_REVIEW_DATA, actual.getResponseStatus());

    }
}