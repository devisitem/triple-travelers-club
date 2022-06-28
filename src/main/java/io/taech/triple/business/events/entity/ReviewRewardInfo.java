package io.taech.triple.business.events.entity;

import io.taech.triple.business.events.constant.MileageUsage;
import io.taech.triple.common.util.Utils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewRewardInfo {

    @Id
    @Column(nullable = false, length = 36)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private TravelersReview review;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mileage_history_id", nullable = false)
    private MileageHistory history;

    @Enumerated(EnumType.STRING)
    private MileageUsage resultType;

    private LocalDateTime createTime;

    public void belongToReview(final TravelersReview review) {
        this.review = review;
    }

    public static ReviewRewardInfo create( final MileageHistory history, final MileageUsage usage) {
        final ReviewRewardInfo rewardInfo = new ReviewRewardInfo();

        rewardInfo.history = history;
        rewardInfo.resultType = usage;
        rewardInfo.createTime = LocalDateTime.now();

        return rewardInfo;
    }

    public boolean isEarlyBirdBonus() {
        return (this.resultType == MileageUsage.ADD_FIRST_BONUS);
    }

    public boolean isNotDeletedHistory() {
        return Utils.isNull(this.history.getDeleteTime());
    }
}
