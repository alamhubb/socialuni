package com.socialuni.social.sdk.dao.utils.common;

import com.socialuni.social.sdk.dao.DO.base.SocialuniUserInfoBaseDO;
import com.socialuni.social.sdk.dao.repository.common.SocialuniCommonRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialuniCommonDOUtil {
    static SocialuniCommonRepository repository;

    @Resource
    public void setRepository(SocialuniCommonRepository repository) {
        SocialuniCommonDOUtil.repository = repository;
    }

    public static <T> T save(T t) {
        return repository.save(t);
    }

    public static <T> T findById(Integer id, Class<T> tClass) {
        return repository.findById(id, tClass);
    }

    public static <T extends SocialuniUserInfoBaseDO> T findByUserId(Integer userId, Class<T> tClass) {
        return repository.findByUserId(userId, tClass);
    }
}