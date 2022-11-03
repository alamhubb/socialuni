package com.socialuni.social.sdk.facade;

import com.socialuni.social.common.sdk.entity.SocialuniUserInfoBaseDO;
import com.socialuni.social.common.sdk.facade.SocialuniRepositoryFacade;

import java.lang.reflect.InvocationTargetException;

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
     * @param userId
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T extends SocialuniUserInfoBaseDO> T findByUserId(Integer userId, Class<T> tClass) {
        T userInfo = null;
        try {
            userInfo = tClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        userInfo.setUserId(userId);
        return findByExample(userInfo).get();
    }

}
