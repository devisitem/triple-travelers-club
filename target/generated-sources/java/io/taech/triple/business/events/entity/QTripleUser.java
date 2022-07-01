package io.taech.triple.business.events.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTripleUser is a Querydsl query type for TripleUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTripleUser extends EntityPathBase<TripleUser> {

    private static final long serialVersionUID = -1094220234L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTripleUser tripleUser = new QTripleUser("tripleUser");

    public final ListPath<MileageHistory, QMileageHistory> histories = this.<MileageHistory, QMileageHistory>createList("histories", MileageHistory.class, QMileageHistory.class, PathInits.DIRECT2);

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final QMileageInfo mileageInfo;

    public final StringPath nickname = createString("nickname");

    public final ListPath<TravelersReview, QTravelersReview> reviews = this.<TravelersReview, QTravelersReview>createList("reviews", TravelersReview.class, QTravelersReview.class, PathInits.DIRECT2);

    public QTripleUser(String variable) {
        this(TripleUser.class, forVariable(variable), INITS);
    }

    public QTripleUser(Path<? extends TripleUser> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTripleUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTripleUser(PathMetadata metadata, PathInits inits) {
        this(TripleUser.class, metadata, inits);
    }

    public QTripleUser(Class<? extends TripleUser> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.mileageInfo = inits.isInitialized("mileageInfo") ? new QMileageInfo(forProperty("mileageInfo"), inits.get("mileageInfo")) : null;
    }

}

