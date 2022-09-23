package com.socialuni.social.sdk.dao.repository.common;

import com.socialuni.social.sdk.dao.DO.base.SocialuniUserInfoBaseDO;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;

@Repository
public class SocialuniCommonRepository {
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

    public <T extends SocialuniUserInfoBaseDO> T findByUserId(Integer userId, Class<T> tClass) {
        T userInfo = null;
        try {
            userInfo = tClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        userInfo.setUserId(userId);

        Example<T> example = Example.of(userInfo);

        return example.getProbe();
    }
}