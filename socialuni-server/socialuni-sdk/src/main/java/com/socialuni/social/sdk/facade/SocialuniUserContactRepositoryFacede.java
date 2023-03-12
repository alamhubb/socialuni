package com.socialuni.social.sdk.facade;

import com.socialuni.social.common.api.entity.SocialuniUserContactBaseDO;
import com.socialuni.social.user.sdk.facede.SocialuniUserRepositoryFacede;

import java.lang.reflect.InvocationTargetException;

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
     * @param userId
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T extends SocialuniUserContactBaseDO> T findByUserIdAndBeUserId(Integer userId, Integer beUserId, Class<T> tClass) {
        T userInfo = null;
        try {
            userInfo = tClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        userInfo.setUserId(userId);
        userInfo.setBeUserId(beUserId);
        return findByExample(userInfo);
    }

}
