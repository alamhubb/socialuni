package com.socialuni.social.sdk.im.dao.facede;

import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class SocialuniMusicRoomRepositoryFacede extends SocialuniRepositoryFacade {

    public static <T> List<T> findAllByRoomIdAndStatus(Long chatId, String status, Class<T> tClass) {

        EntityManager entityManager = getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tClass);
        Root<T> userInfo = criteriaQuery.from(tClass);

        Predicate userIdPredicate = criteriaBuilder.equal(userInfo.get("roomId"), chatId);
        Predicate statusPredicate = criteriaBuilder.equal(userInfo.get("status"), status);

        criteriaQuery.where(userIdPredicate, statusPredicate);
        criteriaQuery.orderBy(criteriaBuilder.asc(userInfo.get("orderNo")));

        List<T> list = entityManager.createQuery(criteriaQuery).getResultList();

        return list;
    }

}
