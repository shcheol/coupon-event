package com.hcs.member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberId is a Querydsl query type for MemberId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QMemberId extends BeanPath<MemberId> {

    private static final long serialVersionUID = 1050504006L;

    public static final QMemberId memberId = new QMemberId("memberId");

    public final StringPath id = createString("id");

    public QMemberId(String variable) {
        super(MemberId.class, forVariable(variable));
    }

    public QMemberId(Path<? extends MemberId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberId(PathMetadata metadata) {
        super(MemberId.class, metadata);
    }

}

