package io.taech.triple.business.events.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    public static TravelersReview create(final String reviewContent) {
        final TravelersReview review = new TravelersReview();
        review.reviewContent = reviewContent;
        review.deleteYn = "N";
        review.createTime = LocalDateTime.now();

        return review;
    }




}
