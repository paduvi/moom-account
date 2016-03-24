package com.chotoxautinh.server.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QEmail is a Querydsl query type for Email
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QEmail extends EntityPathBase<Email> {

    private static final long serialVersionUID = -2048214153L;

    public static final QEmail email = new QEmail("email");

    public final DateTimePath<java.util.Date> birthday = createDateTime("birthday", java.util.Date.class);

    public final StringPath id = createString("id");

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final ListPath<Email.SecurityQuestion, QEmail_SecurityQuestion> questions = this.<Email.SecurityQuestion, QEmail_SecurityQuestion>createList("questions", Email.SecurityQuestion.class, QEmail_SecurityQuestion.class, PathInits.DIRECT2);

    public final StringPath retrieveEmail = createString("retrieveEmail");

    public final StringPath username = createString("username");

    public QEmail(String variable) {
        super(Email.class, forVariable(variable));
    }

    public QEmail(Path<? extends Email> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEmail(PathMetadata<?> metadata) {
        super(Email.class, metadata);
    }

}

