package com.socialuni.social.common.sdk.dao.facede;

import com.socialuni.social.common.api.entity.SocialuniContentBaseDO;
import com.socialuni.social.common.api.entity.SocialuniUserContactBaseDO;
import com.socialuni.social.common.sdk.dao.repository.SocialuniCommonRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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
@Component
public class SocialuniUserContactRepositoryFacede extends SocialuniUserRepositoryFacede {

    static SocialuniCommonRepository socialuniCommonRepository;

    @Resource
    public void setSocialuniCommonRepository(SocialuniCommonRepository socialuniCommonRepository) {
        SocialuniUserContactRepositoryFacede.socialuniCommonRepository = socialuniCommonRepository;
    }


    /**
     * 通过userId获得对应的子类。
     *
     * @param userId
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T extends SocialuniUserContactBaseDO> T findByUserIdAndBeUserId(Long userId, Long beUserId, Class<T> tClass) {
        return socialuniCommonRepository.findByUserIdAndBeUserId(userId, beUserId, tClass);
    }

    public static <T extends SocialuniUserContactBaseDO> T findByUserIdAndBeUserIdAndStatus(Long userId, Long beUserId, String status, Class<T> tClass) {
        return socialuniCommonRepository.findByUserIdAndBeUserIdAndStatus(userId, beUserId, status, tClass);
    }

    public static <T extends SocialuniUserContactBaseDO> Long countByUserIdAndBeUserIdAndStatus(Long userId, Long beUserId, String status, Class<T> tClass) {
        return socialuniCommonRepository.countByUserIdAndBeUserIdAndStatus(userId, beUserId, status, tClass);
    }


    public static <T extends SocialuniUserContactBaseDO> T findByUserIdAndBeUserIdOrderByIdDesc(Long userId, Long beUserId, Class<T> tClass) {
        return socialuniCommonRepository.findByUserIdAndBeUserIdOrderByIdDesc(userId, beUserId, tClass);
    }

    public static <T extends SocialuniUserContactBaseDO> T findByUserIdAndBeUserIdAndNotStatus(Long userId, Long beUserId, String status, Class<T> tClass) {
        return socialuniCommonRepository.findByUserIdAndBeUserIdAndNotStatus(userId, beUserId, status, tClass);
    }
}
