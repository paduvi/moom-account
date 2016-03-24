package com.chotoxautinh.server.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QFaceAccount is a Querydsl query type for FaceAccount
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QFaceAccount extends EntityPathBase<FaceAccount> {

    private static final long serialVersionUID = 689617067L;

    public static final QFaceAccount faceAccount = new QFaceAccount("faceAccount");

    public final StringPath email = createString("email");

    public final StringPath group = createString("group");

    public final StringPath id = createString("id");

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public QFaceAccount(String variable) {
        super(FaceAccount.class, forVariable(variable));
    }

    public QFaceAccount(Path<? extends FaceAccount> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFaceAccount(PathMetadata<?> metadata) {
        super(FaceAccount.class, metadata);
    }

}

