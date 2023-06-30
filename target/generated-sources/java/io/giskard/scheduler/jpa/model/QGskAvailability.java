package io.giskard.scheduler.jpa.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGskAvailability is a Querydsl query type for GskAvailability
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QGskAvailability extends EntityPathBase<GskAvailability> {

    private static final long serialVersionUID = -1594073358L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGskAvailability gskAvailability = new QGskAvailability("gskAvailability");

    public final DateTimePath<java.util.Date> endTime = createDateTime("endTime", java.util.Date.class);

    public final QGskUsers gskUsers;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> isActive = createNumber("isActive", Integer.class);

    public final DateTimePath<java.util.Date> startTime = createDateTime("startTime", java.util.Date.class);

    public QGskAvailability(String variable) {
        this(GskAvailability.class, forVariable(variable), INITS);
    }

    public QGskAvailability(Path<? extends GskAvailability> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGskAvailability(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGskAvailability(PathMetadata metadata, PathInits inits) {
        this(GskAvailability.class, metadata, inits);
    }

    public QGskAvailability(Class<? extends GskAvailability> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.gskUsers = inits.isInitialized("gskUsers") ? new QGskUsers(forProperty("gskUsers")) : null;
    }

}

