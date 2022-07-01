package io.taech.triple.business.events.entity;

import io.taech.triple.business.events.constant.MileageUsage;
import io.taech.triple.common.excpeted.ValidateException;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

@Getter
@Entity
public class TripleUser {

    @Id
    @Column(nullable = false, updatable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID id;

    @Column(nullable = false, length = 18)
    private String nickname;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<TravelersReview> reviews = new ArrayList<>();

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private MileageInfo mileageInfo;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MileageHistory> histories = new ArrayList<>();


    public static TripleUser create(final String nickname) {
        final TripleUser user = new TripleUser();
        user.id = UUID.randomUUID();
        user.nickname = nickname;

        return user;
    }

    public void addReview(final TravelersReview review) {
        this.reviews.add(review);
        review.connectUser(this);
    }


    public String getId() {
        return this.id.toString();
    }

    public void addHistory(final MileageHistory history) {
        this.histories.add(history);
    }

    public void connectMileageInfo(final MileageInfo mileageInfo) {
        this.mileageInfo = mileageInfo;
        mileageInfo.connectUser(this);
    }

    public Optional<TravelersReview> getReview(final UUID reviewId) {
        final Optional<TravelersReview> first = this.getReviews().stream().filter(review -> review.getId().equals(reviewId))
                .findFirst();

        return first;
    }

}
