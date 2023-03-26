package com.socialuni.social.common.sdk.dao.facede;

import com.socialuni.social.common.api.entity.SocialuniContentBaseDO;
import com.socialuni.social.common.api.entity.SocialuniUserInfoBaseDO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author wulinghui
 * @version 1.0
 * @module api-factory
 * @date 2022/9/27 9:54
 * @since 1.0
 */
public abstract class SocialuniUserRepositoryFacede extends SocialuniRepositoryFacade {
    /**
     * 通过userId获得对应的子类。
     *
     * @param userId
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T extends SocialuniUserInfoBaseDO> T findByUserId(Integer userId, Class<T> tClass) {
        T userInfo = null;
        try {
            userInfo = tClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        userInfo.setUserId(userId);
        return SocialuniRepositoryFacade.findByExample(userInfo);
    }

    public static <T extends SocialuniContentBaseDO> List<T> findAllByUserIdAndStatus(Integer userId, String status, Class<T> tClass) {
        T userInfo = null;
        try {
            userInfo = tClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        userInfo.setUserId(userId);
        userInfo.setStatus(status);
        return SocialuniRepositoryFacade.findAllByExample(userInfo);
    }

    public static <T extends SocialuniUserInfoBaseDO> T findFirstByUserIdNotNull(Integer userId, Class<T> tClass) {
        EntityManager entityManager = SocialuniRepositoryFacade.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tClass);
        Root<T> userInfo = criteriaQuery.from(tClass);

        Predicate userIdPredicate = criteriaBuilder.equal(userInfo.get("userId"), userId);
        criteriaQuery.where(userIdPredicate);

        criteriaQuery.orderBy(criteriaBuilder.desc(userInfo.get("id")));

        TypedQuery<T> query = entityManager.createQuery(criteriaQuery);

        return query.setFirstResult(0).setMaxResults(1).getSingleResult();
    }
}
