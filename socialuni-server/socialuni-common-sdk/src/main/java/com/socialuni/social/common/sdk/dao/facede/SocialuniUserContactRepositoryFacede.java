package com.socialuni.social.common.sdk.dao.facede;

import com.socialuni.social.common.api.entity.SocialuniUserContactBaseDO;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author wulinghui
 * @version 1.0
 * @module api-factory
 * @date 2022/9/27 9:54
 * @since 1.0
 */
public abstract class SocialuniUserContactRepositoryFacede extends SocialuniUserRepositoryFacede {
    /**
     * 通过userId获得对应的子类。
     *
     * @param userId
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T extends SocialuniUserContactBaseDO> T findByUserIdAndBeUserId(Integer userId, Integer beUserId, Class<T> tClass) {
        T userInfo = null;
        try {
            userInfo = tClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        userInfo.setUserId(userId);
        userInfo.setBeUserId(beUserId);
        return findByExample(userInfo);
    }

    public static <T extends SocialuniUserContactBaseDO> T findByUserIdAndBeUserIdAndStatus(Integer userId, Integer beUserId, String status, Class<T> tClass) {
        T userInfo = null;
        try {
            userInfo = tClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        userInfo.setUserId(userId);
        userInfo.setBeUserId(beUserId);
        userInfo.setStatus(status);
        return findByExample(userInfo);
    }

    public static <T extends SocialuniUserContactBaseDO> Long countByUserIdAndBeUserIdAndStatus(Integer userId, Integer beUserId, String status, Class<T> tClass) {
        EntityManager entityManager = getEntityManager();
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

    public static <T extends SocialuniUserContactBaseDO> T findByUserIdAndBeUserIdAndNotStatus(Integer userId, Integer beUserId, String status, Class<T> tClass) {
        EntityManager entityManager = getEntityManager();
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
}
