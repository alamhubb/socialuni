package com.socialuni.social.sdk.im.dao.facede;

import com.socialuni.social.common.api.entity.SocialuniBaseDO;
import com.socialuni.social.common.api.entity.SocialuniContentBaseDO;
import com.socialuni.social.common.api.entity.SocialuniUserInfoBaseDO;
import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus;
import com.socialuni.social.common.api.exception.exception.SocialNullUserException;
import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.common.sdk.dao.repository.SocialuniCommonRepository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class SocialuniChatRepositoryFacede extends SocialuniRepositoryFacade {

    static EntityManager entityManager;

    @Resource
    public void setEntityManager(EntityManager entityManager) {
        SocialuniChatRepositoryFacede.entityManager = entityManager;
    }

    public static <T> List<T> findAllByChatIdAndStatus(Integer chatId, String status, Class<T> tClass) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tClass);
        Root<T> userInfo = criteriaQuery.from(tClass);

        Predicate userIdPredicate = criteriaBuilder.equal(userInfo.get("chatId"), chatId);
        Predicate statusPredicate = criteriaBuilder.equal(userInfo.get("status"), status);

        criteriaQuery.where(userIdPredicate, statusPredicate);
        criteriaQuery.orderBy(criteriaBuilder.desc(userInfo.get("no")));

        List<T> list = entityManager.createQuery(criteriaQuery).getResultList();

        return list;
    }

}
