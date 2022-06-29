package io.taech.triple.business.events.repository;

import io.taech.triple.business.events.entity.ReviewRewardInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RewardInfoRepository extends JpaRepository<ReviewRewardInfo, UUID> {

}
