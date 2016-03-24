package com.chotoxautinh.server.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QGroup is a Querydsl query type for Group
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QGroup extends EntityPathBase<Group> {

    private static final long serialVersionUID = -2046204326L;

    public static final QGroup group = new QGroup("group");

    public final StringPath id = createString("id");

    public final NumberPath<Long> lastExecution = createNumber("lastExecution", Long.class);

    public final NumberPath<Integer> nAccounts = createNumber("nAccounts", Integer.class);

    public final StringPath name = createString("name");

    public QGroup(String variable) {
        super(Group.class, forVariable(variable));
    }

    public QGroup(Path<? extends Group> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGroup(PathMetadata<?> metadata) {
        super(Group.class, metadata);
    }

}

