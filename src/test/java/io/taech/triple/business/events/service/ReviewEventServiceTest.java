package io.taech.triple.business.events.service;


import io.taech.triple.business.events.dto.request.EventDto;
import io.taech.triple.business.events.entity.TravelersReview;
import io.taech.triple.business.events.entity.TripleUser;
import io.taech.triple.business.events.repository.UserRepository;
import io.taech.triple.business.events.repository.support.TripleReviewSupport;
import io.taech.triple.common.base.MockingTester;
import io.taech.triple.common.excpeted.EventProcessingException;
import io.taech.triple.common.excpeted.ServiceStatus;
import io.taech.triple.common.excpeted.ValidateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class ReviewEventServiceTest extends MockingTester {

    @InjectMocks
    private ReviewEventService service;

    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("[리뷰 작성 이벤트 처리] 존재하지않는 작성자")
    public void proceedAddEvent1() throws Throwable {
        /* Given */
        final String userId = "7c4f1e5f-22f1-4028-a2e7-5395391b22c5";
        final EventDto input = new EventDto();
        input.setUserId(userId);

        /* When */
        when(userRepository.findById(input.getUserId()))
                .thenReturn(Optional.ofNullable(null));
        final ValidateException actual = assertThrows(ValidateException.class, () -> service.proceedAddEvent(input));

        /* Then */
        assertEquals(ServiceStatus.NOT_FOUND_USER_DATA, actual.getStatus());

    }

    @Test
    @DisplayName("[리뷰 작성 이벤트 처리] 존재하지않는 리뷰")
    public void getReviewAboutEvent2() throws Throwable {
        /* Given */
        final String reviewId = "1bbff29d-69db-404e-b6fb-396de18ebb85";
        final String userId = "7c4f1e5f-22f1-4028-a2e7-5395391b22c5";
        final EventDto event = new EventDto();
        event.setReviewId(reviewId);
        event.setUserId(userId);

        final TripleUser user = new TripleUser();
        ArrayList<TravelersReview> reviews = new ArrayList<>();

        /* When */
        when(userRepository.findById(event.getUserId()))
                .thenReturn(Optional.ofNullable(user));
        final ValidateException actual = assertThrows(ValidateException.class, () -> service.proceedAddEvent(event));
        /* Then */
        assertEquals(ServiceStatus.NOT_FOUND_REVIEW_DATA, actual.getStatus());

    }

    @Test
    @DisplayName("[리뷰 작성 이벤트 처리] 이미 삭제된 리뷰")
    public void getReviewAboutEvent3() throws Throwable {
        /* Given */
        final String reviewId = "1bbff29d-69db-404e-b6fb-396de18ebb85";
        final String userId = "7c4f1e5f-22f1-4028-a2e7-5395391b22c5";
        final EventDto event = new EventDto();
        event.setReviewId(reviewId);
        event.setUserId(userId);


        /* When */

        /* Then */

    }
}