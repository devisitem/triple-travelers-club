package io.taech.triple.business.events.repository.support;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.taech.triple.business.events.entity.QTravelersReview;
import io.taech.triple.business.events.entity.TravelersReview;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public class ReviewRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory query;


    public ReviewRepositorySupport(final JPAQueryFactory query) {
        super(TravelersReview.class);
        this.query = query;
    }


    public TravelersReview findReview(final UUID reviewId, final UUID userId) {
        final QTravelersReview review = QTravelersReview.travelersReview;

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
}
