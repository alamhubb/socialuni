package com.socialuni.social.common.sdk.dao.repository;

import com.socialuni.social.common.api.entity.SocialuniUserContactBaseDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class SocialuniCommonRepository {
    @Resource
    private EntityManager entityManager;

    @Transactional
    public <T> T save(T t) {
        SimpleJpaRepository<T, Integer> simpleJpaRepository = getSimpleJpaRepository(t);
        t = simpleJpaRepository.save(t);
        return t;
    }

    @Transactional
    public <T> void delete(T t) {
        SimpleJpaRepository<T, Integer> simpleJpaRepository = getSimpleJpaRepository(t);
        simpleJpaRepository.delete(t);
    }

    @Transactional
    public <T> void deleteById(Integer id, Class<T> tClass) {
        SimpleJpaRepository<T, Integer> simpleJpaRepository = getSimpleJpaRepository(tClass);
        simpleJpaRepository.deleteById(id);
    }

    public <T> T findById(Integer id, Class<T> tClass) {
        SimpleJpaRepository<T, Integer> simpleJpaRepository = getSimpleJpaRepository(tClass);
        T t = simpleJpaRepository.findById(id).orElse(null);
        return t;
    }

    @Cacheable(cacheNames = "commonRepostoryFindByExample")
    public <T> T findByExample(T exampleObj) {
        SimpleJpaRepository<T, Integer> simpleJpaRepository = getSimpleJpaRepository(exampleObj);
        Example<T> example = Example.of(exampleObj);
        return simpleJpaRepository.findOne(example).orElse(null);
    }

    public <T> Long countByExample(T exampleObj) {
        SimpleJpaRepository<T, Integer> simpleJpaRepository = getSimpleJpaRepository(exampleObj);
        Example<T> example = Example.of(exampleObj);
        return simpleJpaRepository.count(example);
    }


    public <T> T findFirstByExample(Example<T> example,CriteriaQuery<T> criteriaQuery) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }


    /**
     * 获得SimpleJpaRepository
     *
     * @param tClass
     * @param <T>
     * @return
     */
    private <T> SimpleJpaRepository<T, Integer> getSimpleJpaRepository(Class<T> tClass) {
        return new SimpleJpaRepository<>(tClass, entityManager);
    }

    /**
     * 获得SimpleJpaRepository
     *
     * @param t
     * @param <T>
     * @return
     * @see #getSimpleJpaRepository(Class)
     */
    private <T> SimpleJpaRepository<T, Integer> getSimpleJpaRepository(T t) {
        return getSimpleJpaRepository((Class<T>) t.getClass());
    }

    public EntityManager getEntityManager() {
        return this.entityManager;
    }
}