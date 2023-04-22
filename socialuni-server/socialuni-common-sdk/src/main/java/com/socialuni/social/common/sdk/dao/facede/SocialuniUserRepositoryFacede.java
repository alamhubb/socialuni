package com.socialuni.social.common.sdk.dao.facede;

import com.socialuni.social.common.api.entity.SocialuniUserInfoBaseDO;
import com.socialuni.social.common.sdk.dao.repository.SocialuniCommonRepository;

import javax.annotation.Resource;

/**
 * @author wulinghui
 * @version 1.0
 * @module api-factory
 * @date 2022/9/27 9:54
 * @since 1.0
 */
public abstract class SocialuniUserRepositoryFacede extends SocialuniRepositoryFacade {

    static SocialuniCommonRepository socialuniCommonRepository;

    @Resource
    public void setSocialuniCommonUserRepository(SocialuniCommonRepository socialuniCommonRepository) {
        SocialuniUserRepositoryFacede.socialuniCommonRepository = socialuniCommonRepository;
    }

    /**
     * 通过userId获得对应的子类。
     *
     * @param userId
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T extends SocialuniUserInfoBaseDO> T findByUserId(Integer userId, Class<T> tClass) {
        return socialuniCommonRepository.findByUserId(userId, tClass);
    }

    public static <T extends SocialuniUserInfoBaseDO> T findFirstByUserIdNotNull(Integer userId, Class<T> tClass) {
        return socialuniCommonRepository.findFirstByUserIdNotNull(userId, tClass);
    }
}
