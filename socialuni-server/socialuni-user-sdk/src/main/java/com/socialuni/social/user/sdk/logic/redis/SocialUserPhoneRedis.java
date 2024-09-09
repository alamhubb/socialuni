package com.socialuni.social.user.sdk.logic.redis;

import com.socialuni.social.common.api.constant.CommonRedisKey;
import com.socialuni.social.tance.dev.facade.DevAccountFacade;
import com.socialuni.social.user.sdk.dao.DO.SocialUserPhoneDo;
import com.socialuni.social.user.sdk.dao.repository.SocialUserPhoneRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialUserPhoneRedis {
    @Resource
    SocialUserPhoneRepository socialUserPhoneApi;

    public SocialUserPhoneDo findByPhoneNum(String phoneNum) {
//        return socialUserPhoneRepository.findByPhoneNumAndStatus(phoneNum, CommonStatus.enable);
        Integer devId = DevAccountFacade.getDevIdNullElseCenterDevId();
        return socialUserPhoneApi.findByPhoneNumAndDevId(phoneNum, devId);
    }

    @Cacheable(cacheNames = CommonRedisKey.findUserPhoneByUserId, key = "#userId")
    public SocialUserPhoneDo findUserPhoneByUserId(Long userId) {
//        return socialUserPhoneRepository.findByUserIdAndStatus(userId, CommonStatus.enable);
        return socialUserPhoneApi.findByUserId(userId);
    }
}