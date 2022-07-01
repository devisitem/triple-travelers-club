package io.taech.triple.common.service;

import io.taech.triple.business.events.constant.MileageUsage;
import io.taech.triple.business.events.entity.QReviewRewardInfo;
import io.taech.triple.business.events.entity.ReviewRewardInfo;
import io.taech.triple.business.events.entity.TravelersReview;
import io.taech.triple.business.events.entity.TripleUser;
import io.taech.triple.business.events.repository.RewardInfoRepository;
import io.taech.triple.common.base.MockingTester;
import io.taech.triple.common.excpeted.EventProcessingException;
import io.taech.triple.common.excpeted.ServiceStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.sql.SQLClientInfoException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MileageServiceTest extends MockingTester {

    @InjectMocks
    private MileageService mileageService;

    @Mock
    private RewardInfoRepository rewardInfoRepository;


    @Test
    @DisplayName("[마일리지 적립 서비스] 정보 저장내 예외 발생시 기준에러 쓰로우")
    public void manageMileagesWithReview1() throws Throwable {
        /* Given */
        final TravelersReview review = mock(TravelersReview.class);
        final TripleUser user = mock(TripleUser.class);
        final MileageUsage mileageUsage = MileageUsage.ADD_WRITE_REVIEW;
        final ReviewRewardInfo rewardInfo = mock(ReviewRewardInfo.class);

        /* When */
        when(rewardInfoRepository.save(rewardInfo))
                .thenThrow(new RuntimeException("존재하지 않는 컬럼입니당."));
        final EventProcessingException actual = assertThrows(EventProcessingException.class, () -> mileageService.manageMileagesWithReview(review, user, mileageUsage));

        /* Then */
        assertEquals(ServiceStatus.FAILED_TO_MANAGE_MILEAGES, actual.getServiceStatus());

    }

}