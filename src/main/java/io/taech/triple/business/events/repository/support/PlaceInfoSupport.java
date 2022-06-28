package io.taech.triple.business.events.repository.support;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.taech.triple.business.events.entity.QTriplePlaceInfo;
import io.taech.triple.business.events.entity.TriplePlaceInfo;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;


@Repository
public class PlaceInfoSupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory query;
    private final QTriplePlaceInfo place = QTriplePlaceInfo.triplePlaceInfo;

    public PlaceInfoSupport(final JPAQueryFactory query) {
        super(TriplePlaceInfo.class);
        this.query = query;
    }

}
