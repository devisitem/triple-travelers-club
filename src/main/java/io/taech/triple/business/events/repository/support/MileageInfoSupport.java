package io.taech.triple.business.events.repository.support;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.taech.triple.business.events.entity.MileageInfo;
import io.taech.triple.business.events.entity.QMileageInfo;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class MileageInfoSupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory query;
    private QMileageInfo info = QMileageInfo.mileageInfo;

    public MileageInfoSupport(final JPAQueryFactory query) {
        super(MileageInfo.class);
        this.query = query;
    }

    public MileageInfo findByUserId(final UUID userId) {
        query.select(Projections.fields(MileageInfo.class,
                info.id,
                info.mileage
                ))
                .from(info)
                .where(info.userId.eq(userId))
                .fetchOne();
        return null;
    }
}
