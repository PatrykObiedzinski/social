package com.example.social.configuration;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class BaseDaoConfiguration {

    @PersistenceContext
    private EntityManager entityManager;
    private JPAQueryFactory queryFactory;

    protected EntityManager entityManager() {
        return entityManager;
    }

    protected JPAQueryFactory queryFactory() {
        if (queryFactory == null) {
            queryFactory = new JPAQueryFactory(entityManager);
        }
        return queryFactory;
    }
}
