package com.socialuni.social.sdk.dao.repository.common;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
public class SocialuniCommonUnionContentRepository {
    @Resource
    private EntityManager entityManager;

    @Transactional
    public <T> T save(T t) {
        SimpleJpaRepository<T, Integer> simpleJpaRepository = new SimpleJpaRepository<>((Class<T>) t.getClass(), entityManager);
        t = simpleJpaRepository.save(t);
        return t;
    }

    public <T> T findById(Integer id, Class<T> tClass) {
        SimpleJpaRepository<T, Integer> simpleJpaRepository = new SimpleJpaRepository<>(tClass, entityManager);
        T t = (T) simpleJpaRepository.findById(id);
        return t;
    }
}