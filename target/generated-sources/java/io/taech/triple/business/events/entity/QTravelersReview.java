package io.taech.triple.business.events.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTravelersReview is a Querydsl query type for TravelersReview
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTravelersReview extends EntityPathBase<TravelersReview> {

    private static final long serialVersionUID = 1360869815L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTravelersReview travelersReview = new QTravelersReview("travelersReview");

    public final DateTimePath<java.time.LocalDateTime> createTime = createDateTime("createTime", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> deleteTime = createDateTime("deleteTime", java.time.LocalDateTime.class);

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final ListPath<ReviewImages, QReviewImages> images = this.<ReviewImages, QReviewImages>createList("images", ReviewImages.class, QReviewImages.class, PathInits.DIRECT2);

    public final QTriplePlaceInfo place;

    public final StringPath reviewContent = createString("reviewContent");

    public final ListPath<ReviewRewardInfo, QReviewRewardInfo> rewardInfo = this.<ReviewRewardInfo, QReviewRewardInfo>createList("rewardInfo", ReviewRewardInfo.class, QReviewRewardInfo.class, PathInits.DIRECT2);

    public final QTripleUser user;

    public QTravelersReview(String variable) {
        this(TravelersReview.class, forVariable(variable), INITS);
    }

    public QTravelersReview(Path<? extends TravelersReview> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTravelersReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTravelersReview(PathMetadata metadata, PathInits inits) {
        this(TravelersReview.class, metadata, inits);
    }

    public QTravelersReview(Class<? extends TravelersReview> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.place = inits.isInitialized("place") ? new QTriplePlaceInfo(forProperty("place")) : null;
        this.user = inits.isInitialized("user") ? new QTripleUser(forProperty("user"), inits.get("user")) : null;
    }

}

