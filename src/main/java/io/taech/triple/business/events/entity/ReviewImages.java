package io.taech.triple.business.events.entity;

import io.taech.triple.common.util.Utils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewImages {

    @Id
    private UUID id;

    @Column(name = "common_file_id", length = 36, nullable = false)
    private UUID fileId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travelers_review_id", nullable = false)
    private TravelersReview review;

    @Column(nullable = false, length = 255)
    private String imageLink;

    private LocalDateTime createTime;

    private LocalDateTime deleteTime;




    public boolean isDeleted() {
        return Utils.isNotNull(this.deleteTime);
    }

}
