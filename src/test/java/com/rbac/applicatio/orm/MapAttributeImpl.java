package com.rbac.applicatio.orm;

import javax.persistence.metamodel.*;
import java.lang.reflect.Member;

/**
 * @auther ttm
 * @date 2018/8/30 0030
 **/
public class MapAttributeImpl implements MapAttribute {
    @Override
    public Class getKeyJavaType() {
        return null;
    }

    @Override
    public Type getKeyType() {
        return null;
    }

    @Override
    public CollectionType getCollectionType() {
        return null;
    }

    @Override
    public Type getElementType() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public PersistentAttributeType getPersistentAttributeType() {
        return null;
    }

    @Override
    public ManagedType getDeclaringType() {
        return null;
    }

    @Override
    public Class getJavaType() {
        return null;
    }

    @Override
    public Member getJavaMember() {
        return null;
    }

    @Override
    public boolean isAssociation() {
        return false;
    }

    @Override
    public boolean isCollection() {
        return false;
    }

    @Override
    public BindableType getBindableType() {
        return null;
    }

    @Override
    public Class getBindableJavaType() {
        return null;
    }
}
