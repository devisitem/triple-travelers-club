package io.taech.triple.common.service;

import io.taech.triple.business.events.constant.MileageUsage;
import io.taech.triple.business.events.entity.MileageHistory;
import io.taech.triple.business.events.entity.ReviewRewardInfo;
import io.taech.triple.business.events.entity.TravelersReview;
import io.taech.triple.business.events.entity.TripleUser;
import io.taech.triple.business.events.repository.MileageHistoryRepository;
import io.taech.triple.business.events.repository.RewardInfoRepository;
import io.taech.triple.common.excpeted.EventProcessingException;
import io.taech.triple.common.excpeted.ServiceStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MileageService {

    private final MileageHistoryRepository historyRepository;
    private final RewardInfoRepository rewardInfoRepository;

    @Transactional(propagation = Propagation.MANDATORY)
    public void manageMileagesWithReview(final TravelersReview review, final TripleUser user, final MileageUsage usage) throws Throwable {
        try {

            final MileageHistory history = MileageHistory.create(usage, user);
            final ReviewRewardInfo rewardInfo = ReviewRewardInfo.create(usage, review, history);

            user.addHistory(history);
            rewardInfoRepository.save(rewardInfo);


            log.info("[User id:\"{}\"][{}][{}] - managed mileage.", user.getId(), usage.mileage(), usage.descriptions());

        } catch (Throwable t) {
            log.error("Error occurred at some problems with: ", t);
            log.info("Failed to manage for mileage but, propagate this exception as EventProcessingException for transactions.");

            if(t instanceof EventProcessingException)
                throw t;

            throw new EventProcessingException(ServiceStatus.FAILED_TO_MANAGE_MILEAGES);
        }
    }

}
