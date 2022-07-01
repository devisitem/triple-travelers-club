package io.taech.triple.business.events.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMileageHistory is a Querydsl query type for MileageHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMileageHistory extends EntityPathBase<MileageHistory> {

    private static final long serialVersionUID = -738017577L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMileageHistory mileageHistory = new QMileageHistory("mileageHistory");

    public final DateTimePath<java.time.LocalDateTime> createTime = createDateTime("createTime", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> deleteTime = createDateTime("deleteTime", java.time.LocalDateTime.class);

    public final StringPath descriptions = createString("descriptions");

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final NumberPath<Integer> mileage = createNumber("mileage", Integer.class);

    public final QReviewRewardInfo rewardInfo;

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    public final QTripleUser user;

    public QMileageHistory(String variable) {
        this(MileageHistory.class, forVariable(variable), INITS);
    }

    public QMileageHistory(Path<? extends MileageHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMileageHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMileageHistory(PathMetadata metadata, PathInits inits) {
        this(MileageHistory.class, metadata, inits);
    }

    public QMileageHistory(Class<? extends MileageHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.rewardInfo = inits.isInitialized("rewardInfo") ? new QReviewRewardInfo(forProperty("rewardInfo"), inits.get("rewardInfo")) : null;
        this.user = inits.isInitialized("user") ? new QTripleUser(forProperty("user"), inits.get("user")) : null;
    }

}

