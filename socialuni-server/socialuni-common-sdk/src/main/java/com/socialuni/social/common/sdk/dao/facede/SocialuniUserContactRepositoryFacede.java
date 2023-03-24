package com.socialuni.social.common.sdk.dao.facede;

import com.socialuni.social.common.api.entity.SocialuniUserContactBaseDO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniGetUserContactRecordDO;
import com.socialuni.social.common.sdk.dao.repository.SocialuniUserContactFacedeRepository;
import com.socialuni.social.common.sdk.dao.repository.SocialuniUserContactFacedeRepositoryImpl;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;

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
     *
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

    public static <T extends SocialuniUserContactBaseDO> Long countByUserIdAndBeUserIdAndStatus(Integer userId, Integer beUserId, String status, Class<T> tClass) {
        T userInfo = null;
        try {
            userInfo = tClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        //手动创建实例
        SocialuniUserContactFacedeRepositoryImpl<T> socialuniUserContactFacedeRepository = new SocialuniUserContactFacedeRepositoryImpl<>(getEntityManager(), tClass);
        return socialuniUserContactFacedeRepository.getUserRepository().countByUserIdAndBeUserIdAndStatus(userId, beUserId, status);
    }

}
