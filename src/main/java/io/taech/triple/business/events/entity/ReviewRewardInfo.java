package io.taech.triple.business.events.entity;

import io.taech.triple.business.events.constant.MileageUsage;
import io.taech.triple.common.util.Utils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@DynamicInsert
public class ReviewRewardInfo {

    @Id
    @Column(nullable = false, updatable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private TravelersReview review;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "mileage_history_id", nullable = false)
    private MileageHistory history;

    @Enumerated(EnumType.STRING)
    private MileageUsage resultType;

    private LocalDateTime createTime;

    public void belongToReview(final TravelersReview review) {
        this.review = review;
    }

    public static ReviewRewardInfo create(final MileageUsage usage, final TravelersReview review, final MileageHistory history) {
        final ReviewRewardInfo rewardInfo = new ReviewRewardInfo();

        rewardInfo.id = UUID.randomUUID();
        rewardInfo.resultType = usage;
        rewardInfo.review = review;
        rewardInfo.history = history;

        return rewardInfo;
    }

    public boolean isEarlyBirdBonus() {
        return (this.resultType == MileageUsage.ADD_FIRST_BONUS);
    }

    public boolean isNotDeletedHistory() {
        return Utils.isNull(this.history.getDeleteTime());
    }

    public void connectHistory(MileageHistory history) {
        this.history = history;
    }
}
