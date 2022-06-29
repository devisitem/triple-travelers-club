package io.taech.triple.business.events.repository.support;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.taech.triple.business.events.entity.QTripleUser;
import io.taech.triple.business.events.entity.TripleUser;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class TripleUserSupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory query;
    private final QTripleUser user = QTripleUser.tripleUser;

    public TripleUserSupport(final JPAQueryFactory query) {
        super(TripleUser.class);
        this.query = query;
    }

    public TripleUser findById(final UUID userId) {

        return query
                .select(Projections.fields(TripleUser.class,
                        user.id,
                        user.nickname
                        ))
                .from(user)
                .where(user.id.eq(userId))
                .fetchOne();
    }
}
