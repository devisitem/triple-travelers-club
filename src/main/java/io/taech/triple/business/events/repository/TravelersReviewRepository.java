package io.taech.triple.business.events.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.taech.triple.business.events.entity.QTravelersReview;
import io.taech.triple.business.events.entity.TravelersReview;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class TravelersReviewRepository extends QuerydslRepositorySupport {

    private final JPAQueryFactory qeury;
    p


    public TravelersReviewRepository(final JPAQueryFactory qeury) {
        super(TravelersReview.class);
        this.qeury = qeury;
    }

    public List<TravelersReview> findByReviewId(final String reviewId) {

        return null;
    }
}
