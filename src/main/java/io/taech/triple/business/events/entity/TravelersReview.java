package io.taech.triple.business.events.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TravelersReview {

    @Id
    @Column(nullable = false, length = 36)
    private UUID id;

    @Column(nullable = false, length = 36)
    private UUID userId;

    @Column(nullable = false, length = 36)
    private UUID placeId;

    @Column(nullable = false)
    private String reviewContent;

    @Column(nullable = false)
    private String deleteYn;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createTime;

}
