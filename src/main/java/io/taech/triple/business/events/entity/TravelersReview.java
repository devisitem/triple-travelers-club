package io.taech.triple.business.events.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class TravelersReview {

    @Id
    @Column(nullable = false, columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false)
    private String reviewContent;

    @Column(nullable = false)
    private String deleteYn;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createTime;



}
