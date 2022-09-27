package com.socialuni.social.common.component;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public class SocialuniCommonRepository {
    @Resource
    private EntityManager entityManager;

    @Transactional
    public <T> T save(T t) {
        SimpleJpaRepository<T, Integer> simpleJpaRepository = getSimpleJpaRepository(t);
        t = simpleJpaRepository.save(t);
        return t;
    }

    public <T> T findById(Integer id, Class<T> tClass) {
        SimpleJpaRepository<T, Integer> simpleJpaRepository = getSimpleJpaRepository(tClass);
        T t = (T) simpleJpaRepository.findById(id);
        return t;
    }

    public <T> Optional<T> findByExample(T exampleObj) {
        SimpleJpaRepository<T, Integer> simpleJpaRepository = getSimpleJpaRepository(exampleObj);
        Example<T> example = Example.of(exampleObj);
        return simpleJpaRepository.findOne(example);
    }
    
    /**
     * 获得SimpleJpaRepository
     * @param tClass
     * @param <T>
     * @return
     */
    private <T> SimpleJpaRepository<T, Integer> getSimpleJpaRepository(Class<T> tClass) {
        return new SimpleJpaRepository<>(tClass, entityManager);
    }

    /**获得SimpleJpaRepository
     * @see #getSimpleJpaRepository(Class)
     * @param t
     * @param <T>
     * @return
     */
    private <T> SimpleJpaRepository<T, Integer> getSimpleJpaRepository(T t) {
        return getSimpleJpaRepository((Class<T>) t.getClass());
    }

}