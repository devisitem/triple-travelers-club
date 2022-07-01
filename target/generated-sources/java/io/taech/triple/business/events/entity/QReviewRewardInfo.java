package io.taech.triple.business.events.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReviewRewardInfo is a Querydsl query type for ReviewRewardInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReviewRewardInfo extends EntityPathBase<ReviewRewardInfo> {

    private static final long serialVersionUID = 1960300834L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReviewRewardInfo reviewRewardInfo = new QReviewRewardInfo("reviewRewardInfo");

    public final DateTimePath<java.time.LocalDateTime> createTime = createDateTime("createTime", java.time.LocalDateTime.class);

    public final QMileageHistory history;

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final EnumPath<io.taech.triple.business.events.constant.MileageUsage> resultType = createEnum("resultType", io.taech.triple.business.events.constant.MileageUsage.class);

    public final QTravelersReview review;

    public QReviewRewardInfo(String variable) {
        this(ReviewRewardInfo.class, forVariable(variable), INITS);
    }

    public QReviewRewardInfo(Path<? extends ReviewRewardInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReviewRewardInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReviewRewardInfo(PathMetadata metadata, PathInits inits) {
        this(ReviewRewardInfo.class, metadata, inits);
    }

    public QReviewRewardInfo(Class<? extends ReviewRewardInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.history = inits.isInitialized("history") ? new QMileageHistory(forProperty("history"), inits.get("history")) : null;
        this.review = inits.isInitialized("review") ? new QTravelersReview(forProperty("review"), inits.get("review")) : null;
    }

}

