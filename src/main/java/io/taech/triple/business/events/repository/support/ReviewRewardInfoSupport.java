package io.taech.triple.business.events.repository.support;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.taech.triple.business.events.constant.MileageUsage;
import io.taech.triple.business.events.entity.QReviewRewardInfo;
import io.taech.triple.business.events.entity.QTravelersReview;
import io.taech.triple.business.events.entity.ReviewRewardInfo;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class ReviewRewardInfoSupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory query;
    private final QReviewRewardInfo reward = QReviewRewardInfo.reviewRewardInfo;
    private final QTravelersReview review = QTravelersReview.travelersReview;

    public ReviewRewardInfoSupport(final JPAQueryFactory query) {
        super(ReviewRewardInfo.class);
        this.query = query;
    }


    public ReviewRewardInfo findAlreadyRewardedPlace(final UUID placeId, final UUID userId) {
        return query
                .select(Projections.fields(ReviewRewardInfo.class,
                        reward.id
                ))
                .from(review)
                .innerJoin(reward).on(review.id.eq(reward.review.id))
                .where(review.place.id.eq(placeId)
                        .and(review.user.id.eq(userId))
                        .and(reward.resultType.eq(MileageUsage.ADD_WRITE_REVIEW)))
                .fetchOne();
    }

}
