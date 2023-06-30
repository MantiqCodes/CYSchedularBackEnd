package io.giskard.scheduler.jpa.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGskReservation is a Querydsl query type for GskReservation
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QGskReservation extends EntityPathBase<GskReservation> {

    private static final long serialVersionUID = -570561707L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGskReservation gskReservation = new QGskReservation("gskReservation");

    public final StringPath email = createString("email");

    public final DateTimePath<java.util.Date> endTime = createDateTime("endTime", java.util.Date.class);

    public final QGskUsers gskUsers;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> isActive = createNumber("isActive", Integer.class);

    public final NumberPath<Integer> isComplete = createNumber("isComplete", Integer.class);

    public final DateTimePath<java.util.Date> startTime = createDateTime("startTime", java.util.Date.class);

    public final StringPath title = createString("title");

    public QGskReservation(String variable) {
        this(GskReservation.class, forVariable(variable), INITS);
    }

    public QGskReservation(Path<? extends GskReservation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGskReservation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGskReservation(PathMetadata metadata, PathInits inits) {
        this(GskReservation.class, metadata, inits);
    }

    public QGskReservation(Class<? extends GskReservation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.gskUsers = inits.isInitialized("gskUsers") ? new QGskUsers(forProperty("gskUsers")) : null;
    }

}

