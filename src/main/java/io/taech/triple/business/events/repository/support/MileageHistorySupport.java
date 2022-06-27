package io.taech.triple.business.events.repository.support;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.taech.triple.business.events.entity.MileageHistory;
import io.taech.triple.business.events.entity.QMileageHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class MileageHistorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory query;

    public MileageHistorySupport (final JPAQueryFactory query) {
        super(MileageHistory.class);
        this.query = query;
    }

    public List<MileageHistory> findByUserId(final UUID userId) {
        final QMileageHistory history = QMileageHistory.mileageHistory;

        return query.select(Projections.fields(MileageHistory.class,
                history.userId,
                history.mileage
        )).from(history)
                .where(history.userId.eq(userId))
                .fetch();
    }


}
