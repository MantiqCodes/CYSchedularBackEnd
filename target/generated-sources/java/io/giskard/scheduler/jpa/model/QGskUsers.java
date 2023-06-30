package io.giskard.scheduler.jpa.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QGskUsers is a Querydsl query type for GskUsers
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QGskUsers extends EntityPathBase<GskUsers> {

    private static final long serialVersionUID = -652095407L;

    public static final QGskUsers gskUsers = new QGskUsers("gskUsers");

    public final DateTimePath<java.util.Date> dateEntered = createDateTime("dateEntered", java.util.Date.class);

    public final StringPath email = createString("email");

    public final StringPath firstName = createString("firstName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> isActive = createNumber("isActive", Integer.class);

    public final StringPath lastName = createString("lastName");

    public final StringPath phoneNumber = createString("phoneNumber");

    public QGskUsers(String variable) {
        super(GskUsers.class, forVariable(variable));
    }

    public QGskUsers(Path<? extends GskUsers> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGskUsers(PathMetadata metadata) {
        super(GskUsers.class, metadata);
    }

}

