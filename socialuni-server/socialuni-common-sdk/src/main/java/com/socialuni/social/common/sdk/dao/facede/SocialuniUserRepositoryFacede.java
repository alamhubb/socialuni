package com.socialuni.social.common.sdk.dao.facede;

import com.socialuni.social.common.api.entity.SocialuniUserInfoBaseDO;
import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus;
import com.socialuni.social.common.api.exception.exception.SocialNullUserException;
import com.socialuni.social.common.sdk.dao.repository.SocialuniCommonRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wulinghui
 * @version 1.0
 * @module api-factory
 * @date 2022/9/27 9:54
 * @since 1.0
 */
@Component
public class SocialuniUserRepositoryFacede extends SocialuniRepositoryFacade {

    static SocialuniCommonRepository socialuniCommonRepository;

    @Resource
    public void setSocialuniCommonRepository(SocialuniCommonRepository socialuniCommonRepository) {
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
        return socialuniCommonRepository.findFirstByUserId(userId, tClass);
    }

    public static <T extends SocialuniUserInfoBaseDO> T findByUserIdNotNull(Integer userId, Class<T> tClass) {
        T t = socialuniCommonRepository.findFirstByUserId(userId, tClass);
        if (t == null) {
            throw new SocialNullUserException();
        }
        return t;
    }

    public static <T> List<T> findByAllByUserIdAndStatusOrderByIdDesc(Integer userId, Class<T> tClass) {
        return socialuniCommonRepository.findByAllByUserIdAndStatusOrderByIdDesc(userId, SocialuniCommonStatus.enable, tClass);
    }

}
