package com.example.social.configuration;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class BaseDaoConfiguration {

    @PersistenceContext
    private EntityManager entityManager;
    private JPAQueryFactory queryFactory;

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    protected JPAQueryFactory getQueryFactory() {
        if (queryFactory == null) {
            queryFactory = new JPAQueryFactory(entityManager);
        }
        return queryFactory;
    }
}
