package io.taech.triple.business.events.entity;

import io.taech.triple.business.events.constant.ActionType;
import io.taech.triple.business.events.constant.MileageUsage;
import io.taech.triple.common.excpeted.EventProcessingException;
import io.taech.triple.common.util.Utils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

@Getter
@Entity
public class TravelersReview {

    @Id
    @Column(nullable = false, updatable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, updatable = false, columnDefinition = "VARCHAR(36)")
    private TripleUser user;

    @JoinColumn(name = "place_id",nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private TriplePlaceInfo place;

    @Column(nullable = false)
    private String reviewContent;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createTime;

    private LocalDateTime deleteTime;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReviewRewardInfo> rewardInfo = new ArrayList<>();

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReviewImages> images = new ArrayList<>();

    public static TravelersReview create(final String content) {
        final TravelersReview review = new TravelersReview();

        review.id = UUID.randomUUID();
        review.reviewContent = content;

        return review;
    }

    public void connectPlace(final TriplePlaceInfo place) {
        this.place = place;
    }


    public void addRewardInfo(final ReviewRewardInfo rewardInfo) {
        this.rewardInfo.add(rewardInfo);
        rewardInfo.belongToReview(this);
    }

    public void connectUser(final TripleUser tripleUser) {
        this.user = tripleUser;
    }

    public boolean hasDeletedImages() {
        final Long count = this.images.stream().filter(img -> img.isDeleted()).count();

        return (0 < count);
    }

    public Optional<ReviewRewardInfo> getEarlyBirdBonusHistory() {
        return this.rewardInfo.stream()
                .filter(info -> info.isEarlyBirdBonus() && info.isNotDeletedHistory())
                .findFirst();
    }

    public boolean isEqualsId(final UUID other) {
        return this.id.equals(other);
    }

    public TravelersReview ifDeleted(Supplier<? extends EventProcessingException> ifDeletedReview) {
        if(Utils.isNotNull(this.getDeleteTime()))
            throw ifDeletedReview.get();

        return this;
    }

    public Optional<ReviewRewardInfo> ifHasNotDefaultRewardedHistory() {
        return this.getRewardInfo().stream().filter(info -> info.getResultType().equals(MileageUsage.ADD_WRITE_REVIEW))
                .findFirst();
    }

    public boolean hasImages() {
        return ( ! this.images.isEmpty());
    }

}
