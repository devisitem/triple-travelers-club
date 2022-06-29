package io.taech.triple.business.events.repository;

import io.taech.triple.business.events.entity.TripleUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<TripleUser, UUID> {

}
