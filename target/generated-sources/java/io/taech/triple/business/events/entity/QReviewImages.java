package io.taech.triple.business.events.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReviewImages is a Querydsl query type for ReviewImages
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReviewImages extends EntityPathBase<ReviewImages> {

    private static final long serialVersionUID = -1307478627L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReviewImages reviewImages = new QReviewImages("reviewImages");

    public final DateTimePath<java.time.LocalDateTime> createTime = createDateTime("createTime", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> deleteTime = createDateTime("deleteTime", java.time.LocalDateTime.class);

    public final ComparablePath<java.util.UUID> fileId = createComparable("fileId", java.util.UUID.class);

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final StringPath imageLink = createString("imageLink");

    public final QTravelersReview review;

    public QReviewImages(String variable) {
        this(ReviewImages.class, forVariable(variable), INITS);
    }

    public QReviewImages(Path<? extends ReviewImages> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReviewImages(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReviewImages(PathMetadata metadata, PathInits inits) {
        this(ReviewImages.class, metadata, inits);
    }

    public QReviewImages(Class<? extends ReviewImages> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.review = inits.isInitialized("review") ? new QTravelersReview(forProperty("review"), inits.get("review")) : null;
    }

}

