package com.socialuni.social.common.sdk.dao.facede;

import com.qingchi.qing.jpa.SocialuniContentBaseDO;
import com.qingchi.qing.jpa.SocialuniUserInfoBaseDO;
import com.qingchi.qing.constant.SocialuniCommonStatus;
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

    public static <T extends SocialuniContentBaseDO> T findByUserIdAndCustomFieldAndStatus(Long userId, String filedName, Integer fieldId, String status, Class<T> tClass) {
        return socialuniCommonRepository.findByUserIdAndCustomFieldAndStatus(userId, filedName, fieldId, status, tClass);
    }

    public static <T extends SocialuniContentBaseDO> T findByUserIdAndCustomFieldAndStatus(Long userId, String filedName, Long fieldId, String status, Class<T> tClass) {
        return socialuniCommonRepository.findByUserIdAndCustomFieldAndStatus(userId, filedName, fieldId, status, tClass);
    }

    public static <T extends SocialuniContentBaseDO> T findByUserIdAndCustomFieldAndStatus(Long userId, String filedName, String fieldValue, String status, Class<T> tClass) {
        return socialuniCommonRepository.findByUserIdAndCustomFieldAndStatus(userId, filedName, fieldValue, status, tClass);
    }

    public static <T extends SocialuniContentBaseDO> T findByCustomFieldAndStatus(String filedName, String fieldValue, String status, Class<T> tClass) {
        return socialuniCommonRepository.findByCustomFieldAndStatus(filedName, fieldValue, status, tClass);
    }


    /**
     * 通过userId获得对应的子类。
     *
     * @param userId
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T extends SocialuniUserInfoBaseDO> T findFirstByUserIdOrderByIdDesc(Long userId, Class<T> tClass) {
        return socialuniCommonRepository.findFirstByUserIdOrderByIdDesc(userId, tClass);
    }

    public static <T extends SocialuniUserInfoBaseDO> T findByUserIdNotNull(Long userId, Class<T> tClass) {
        T t = socialuniCommonRepository.findFirstByUserIdOrderByIdDesc(userId, tClass);
        if (t == null) {
            throw new SocialNullUserException();
        }
        return t;
    }

    public static <T extends SocialuniUserInfoBaseDO> T findByUserIdAllNull(Long userId, Class<T> tClass) {
        T t = socialuniCommonRepository.findFirstByUserIdOrderByIdDesc(userId, tClass);
        return t;
    }

    public static <T> List<T> findByAllByUserIdAndStatusOrderByIdDesc(Long userId, Class<T> tClass) {
        return socialuniCommonRepository.findByAllByUserIdAndStatusOrderByIdDesc(userId, SocialuniCommonStatus.enable, tClass);
    }

}
