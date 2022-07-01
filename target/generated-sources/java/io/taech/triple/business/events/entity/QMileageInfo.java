package io.taech.triple.business.events.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMileageInfo is a Querydsl query type for MileageInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMileageInfo extends EntityPathBase<MileageInfo> {

    private static final long serialVersionUID = 1976002891L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMileageInfo mileageInfo = new QMileageInfo("mileageInfo");

    public final DateTimePath<java.time.LocalDateTime> createTime = createDateTime("createTime", java.time.LocalDateTime.class);

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final NumberPath<Integer> mileage = createNumber("mileage", Integer.class);

    public final QTripleUser user;

    public QMileageInfo(String variable) {
        this(MileageInfo.class, forVariable(variable), INITS);
    }

    public QMileageInfo(Path<? extends MileageInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMileageInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMileageInfo(PathMetadata metadata, PathInits inits) {
        this(MileageInfo.class, metadata, inits);
    }

    public QMileageInfo(Class<? extends MileageInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QTripleUser(forProperty("user"), inits.get("user")) : null;
    }

}

