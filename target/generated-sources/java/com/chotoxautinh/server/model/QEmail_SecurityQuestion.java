package com.chotoxautinh.server.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QEmail_SecurityQuestion is a Querydsl query type for SecurityQuestion
 */
@Generated("com.mysema.query.codegen.EmbeddableSerializer")
public class QEmail_SecurityQuestion extends BeanPath<Email.SecurityQuestion> {

    private static final long serialVersionUID = 759218429L;

    public static final QEmail_SecurityQuestion securityQuestion = new QEmail_SecurityQuestion("securityQuestion");

    public final StringPath answer = createString("answer");

    public final StringPath question = createString("question");

    public QEmail_SecurityQuestion(String variable) {
        super(Email.SecurityQuestion.class, forVariable(variable));
    }

    public QEmail_SecurityQuestion(Path<? extends Email.SecurityQuestion> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEmail_SecurityQuestion(PathMetadata<?> metadata) {
        super(Email.SecurityQuestion.class, metadata);
    }

}

