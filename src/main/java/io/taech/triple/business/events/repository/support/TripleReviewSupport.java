package io.taech.triple.business.events.repository.support;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.taech.triple.business.events.entity.QTravelersReview;
import io.taech.triple.business.events.entity.TravelersReview;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public class TripleReviewSupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory query;
    final QTravelersReview review = QTravelersReview.travelersReview;


    public TripleReviewSupport(final JPAQueryFactory query) {
        super(TravelersReview.class);
        this.query = query;
    }


    public TravelersReview findReview(final UUID reviewId, final UUID userId) {

        return query.select(Projections.fields(TravelersReview.class,
                        review.id,
                        review.reviewContent
                ))
                .from(review)
                .where(review.id.eq(reviewId)
                        .and(review.userId.eq(userId)))
                .orderBy(review.createTime.desc())
                .fetchOne();
    }

    public TravelersReview findFirstReview(final UUID placeId) {

        return query
                .select(Projections.fields(TravelersReview.class,
                        review.placeId,
                        review.id
                ))
                .from(review)
                .where(review.placeId.eq(placeId)
                        .and(review.deleteTime.isNotNull()))
                .orderBy(review.createTime.asc())
                .limit(1L)
                .fetchOne();

    }
}
