package com.socialuni.social.common.sdk.dao.repository;

import com.socialuni.social.common.api.constant.CommonRedisKey;
import com.socialuni.social.common.api.entity.SocialuniBaseDO;
import com.socialuni.social.common.api.entity.SocialuniUnionContentBaseDO;
import com.socialuni.social.common.api.entity.SocialuniUserContactBaseDO;
import com.socialuni.social.common.api.entity.SocialuniUserInfoBaseDO;
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

    /*@Cacheable(cacheNames = "commonRepostoryFindByExample",key = "#exampleObj.toString()")
    public <T> T findByExample(T exampleObj) {
        SimpleJpaRepository<T, Integer> simpleJpaRepository = getSimpleJpaRepository(exampleObj);
        Example<T> example = Example.of(exampleObj);
        return simpleJpaRepository.findOne(example).orElse(null);
    }*/

    /*public <T> List<T> findAllByExample(T exampleObj) {
        SimpleJpaRepository<T, Integer> simpleJpaRepository = getSimpleJpaRepository(exampleObj);
        Example<T> example = Example.of(exampleObj);
        return simpleJpaRepository.findAll(example);
    }*/

    public <T> Long countByExample(T exampleObj) {
        SimpleJpaRepository<T, Integer> simpleJpaRepository = getSimpleJpaRepository(exampleObj);
        Example<T> example = Example.of(exampleObj);
        return simpleJpaRepository.count(example);
    }


    public <T> T findFirstByExample(Example<T> example, CriteriaQuery<T> criteriaQuery) {
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


    //使用这个的问题是缓存不会被清理
    public <T extends SocialuniUserInfoBaseDO> T findByUserId(Integer userId, Class<T> tClass) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tClass);
        Root<T> userInfo = criteriaQuery.from(tClass);

        Predicate userIdPredicate = criteriaBuilder.equal(userInfo.get("userId"), userId);
        criteriaQuery.where(userIdPredicate);

        List<T> list = entityManager.createQuery(criteriaQuery).setFirstResult(0).setMaxResults(1).getResultList();

        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public <T extends SocialuniUnionContentBaseDO> T findByUnionId(Integer unionId, Class<T> tClass) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tClass);
        Root<T> userInfo = criteriaQuery.from(tClass);

        Predicate userIdPredicate = criteriaBuilder.equal(userInfo.get("unionId"), unionId);
        criteriaQuery.where(userIdPredicate);

        List<T> list = entityManager.createQuery(criteriaQuery).setFirstResult(0).setMaxResults(1).getResultList();

        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }


    public <T extends SocialuniUserInfoBaseDO> T findFirstByUserIdNotNull(Integer userId, Class<T> tClass) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tClass);
        Root<T> userInfo = criteriaQuery.from(tClass);

        Predicate userIdPredicate = criteriaBuilder.equal(userInfo.get("userId"), userId);
        criteriaQuery.where(userIdPredicate);

        criteriaQuery.orderBy(criteriaBuilder.desc(userInfo.get("id")));

        TypedQuery<T> query = entityManager.createQuery(criteriaQuery);

        return query.setFirstResult(0).setMaxResults(1).getSingleResult();
    }


    /**
     * 通过userId获得对应的子类。
     *
     * @param userId
     * @param tClass
     * @param <T>
     * @return
     */
//    @Cacheable(cacheNames = CommonRedisKey.commonFindByUserIdAndBeUserId, key = "#tClass.getSimpleName()+'-'+#userId+'-'+#beUserId")
    public <T extends SocialuniUserContactBaseDO> T findByUserIdAndBeUserId(Integer userId, Integer beUserId, Class<T> tClass) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tClass);
        Root<T> userInfo = criteriaQuery.from(tClass);

        Predicate userIdPredicate = criteriaBuilder.equal(userInfo.get("userId"), userId);
        Predicate beUserIdPredicate = criteriaBuilder.equal(userInfo.get("beUserId"), beUserId);
        criteriaQuery.where(userIdPredicate, beUserIdPredicate);

        List<T> list = entityManager.createQuery(criteriaQuery).setFirstResult(0).setMaxResults(1).getResultList();

        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public <T extends SocialuniUserContactBaseDO> T findByUserIdAndBeUserIdAndStatus(Integer userId, Integer beUserId, String status, Class<T> tClass) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tClass);
        Root<T> userInfo = criteriaQuery.from(tClass);

        Predicate userIdPredicate = criteriaBuilder.equal(userInfo.get("userId"), userId);
        Predicate beUserIdPredicate = criteriaBuilder.equal(userInfo.get("beUserId"), beUserId);
        Predicate statusPredicate = criteriaBuilder.equal(userInfo.get("status"), status);
        criteriaQuery.where(userIdPredicate, beUserIdPredicate, statusPredicate);

        List<T> list = entityManager.createQuery(criteriaQuery).setFirstResult(0).setMaxResults(1).getResultList();

        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public <T extends SocialuniUserContactBaseDO> Long countByUserIdAndBeUserIdAndStatus(Integer userId, Integer beUserId, String status, Class<T> tClass) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<T> userInfo = criteriaQuery.from(tClass);

        criteriaQuery.select(criteriaBuilder.count(userInfo));

        Predicate userIdPredicate = criteriaBuilder.equal(userInfo.get("userId"), userId);
        Predicate beUserIdPredicate = criteriaBuilder.equal(userInfo.get("beUserId"), beUserId);
        Predicate statusPredicate = criteriaBuilder.equal(userInfo.get("status"), status);
        criteriaQuery.where(userIdPredicate, beUserIdPredicate, statusPredicate);

//        criteriaQuery.orderBy(criteriaBuilder.desc(userInfo.get("id")));

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }


    public <T extends SocialuniUserContactBaseDO> T findByUserIdAndBeUserIdOrderByIdDesc(Integer userId, Integer beUserId, Class<T> tClass) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tClass);
        Root<T> userInfo = criteriaQuery.from(tClass);

        Predicate userIdPredicate = criteriaBuilder.equal(userInfo.get("userId"), userId);
        Predicate beUserIdPredicate = criteriaBuilder.equal(userInfo.get("beUserId"), beUserId);
        criteriaQuery.where(userIdPredicate, beUserIdPredicate);

        criteriaQuery.orderBy(criteriaBuilder.desc(userInfo.get("id")));

        List<T> list = entityManager.createQuery(criteriaQuery).setFirstResult(0).setMaxResults(1).getResultList();

        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public <T extends SocialuniUserContactBaseDO> T findByUserIdAndBeUserIdAndNotStatus(Integer userId, Integer beUserId, String status, Class<T> tClass) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tClass);
        Root<T> userInfo = criteriaQuery.from(tClass);

        Predicate userIdPredicate = criteriaBuilder.equal(userInfo.get("userId"), userId);
        Predicate beUserIdPredicate = criteriaBuilder.equal(userInfo.get("beUserId"), beUserId);
        Predicate statusPredicate = criteriaBuilder.notEqual(userInfo.get("status"), status);
        criteriaQuery.where(userIdPredicate, beUserIdPredicate, statusPredicate);

        criteriaQuery.orderBy(criteriaBuilder.desc(userInfo.get("id")));

        List<T> list = entityManager.createQuery(criteriaQuery).setFirstResult(0).setMaxResults(1).getResultList();

        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public <T> List<T> findByAllByIdDesc(Class<T> tClass) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tClass);
        Root<T> userInfo = criteriaQuery.from(tClass);

        criteriaQuery.orderBy(criteriaBuilder.desc(userInfo.get("id")));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

}