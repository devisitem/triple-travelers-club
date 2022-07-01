package io.taech.triple.business.events.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTriplePlaceInfo is a Querydsl query type for TriplePlaceInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTriplePlaceInfo extends EntityPathBase<TriplePlaceInfo> {

    private static final long serialVersionUID = -953940278L;

    public static final QTriplePlaceInfo triplePlaceInfo = new QTriplePlaceInfo("triplePlaceInfo");

    public final DateTimePath<java.time.LocalDateTime> createTime = createDateTime("createTime", java.time.LocalDateTime.class);

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final StringPath placeName = createString("placeName");

    public final ListPath<TravelersReview, QTravelersReview> reviews = this.<TravelersReview, QTravelersReview>createList("reviews", TravelersReview.class, QTravelersReview.class, PathInits.DIRECT2);

    public QTriplePlaceInfo(String variable) {
        super(TriplePlaceInfo.class, forVariable(variable));
    }

    public QTriplePlaceInfo(Path<? extends TriplePlaceInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTriplePlaceInfo(PathMetadata metadata) {
        super(TriplePlaceInfo.class, metadata);
    }

}

