package io.taech.triple.business.events.repository;

import io.taech.triple.business.events.entity.MileageInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface MileageInfoRepository extends JpaRepository<MileageInfo, UUID> {
}
