package io.taech.triple.business.events.service;

import io.taech.triple.business.events.constant.MileageUsage;
import io.taech.triple.business.events.entity.MileageHistory;
import io.taech.triple.business.events.entity.MileageInfo;
import io.taech.triple.business.events.repository.MileageHistoryRepository;
import io.taech.triple.business.events.repository.MileageInfoRepository;
import io.taech.triple.business.events.repository.support.MileageHistorySupport;
import io.taech.triple.business.events.repository.support.MileageInfoSupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class MileageService {

    private final MileageHistoryRepository historyRepository;
    private final MileageHistorySupport historySupport;
    private final MileageInfoSupport infoSupport;

    @Transactional(propagation = Propagation.MANDATORY)
    public void manageMileage(final UUID userId, final MileageUsage usage) throws Throwable {

        final MileageHistory saved = historyRepository.save(MileageHistory.create(userId, usage));
        log.info("[User id:\"{}\"][{}][{}] - managed mileage.", userId, usage.mileage(), usage.descriptions());
        final MileageInfo foundInfo = infoSupport.findByUserId(saved.getUserId());

        final List<MileageHistory> histories = historySupport.findByUserId(userId);
        foundInfo.settledMileages(histories);

    }
}
