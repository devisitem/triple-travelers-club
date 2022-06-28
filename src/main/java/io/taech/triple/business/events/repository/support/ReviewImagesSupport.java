package io.taech.triple.business.events.repository.support;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.taech.triple.business.events.entity.QReviewImages;
import io.taech.triple.business.events.entity.ReviewImages;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ReviewImagesSupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory query;
    private final QReviewImages reviewImg = QReviewImages.reviewImages;


    public ReviewImagesSupport(final JPAQueryFactory query) {
        super(ReviewImages.class);
        this.query = query;
    }


}
