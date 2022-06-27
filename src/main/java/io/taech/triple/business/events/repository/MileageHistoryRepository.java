package io.taech.triple.business.events.repository;

import io.taech.triple.business.events.entity.MileageHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface MileageHistoryRepository extends JpaRepository<MileageHistory, UUID> {

}
