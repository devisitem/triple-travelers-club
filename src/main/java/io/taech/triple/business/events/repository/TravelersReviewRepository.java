package io.taech.triple.business.events.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.taech.triple.business.events.entity.QTravelersReview;
import io.taech.triple.business.events.entity.TravelersReview;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static io.taech.triple.business.events.entity.QTravelersReview.travelersReview;


@Repository
public class TravelersReviewRepository extends QuerydslRepositorySupport {

    private final JPAQueryFactory query;


    public TravelersReviewRepository(final JPAQueryFactory query) {
        super(TravelersReview.class);
        this.query = query;
    }

    public TravelersReview findByReviewId(final UUID reviewId) {

        return query.selectFrom(travelersReview)
                .where(travelersReview.id.eq(reviewId))
                .orderBy(travelersReview.createTime.desc())
                .limit(1L)
                .fetchOne();
    }
}
