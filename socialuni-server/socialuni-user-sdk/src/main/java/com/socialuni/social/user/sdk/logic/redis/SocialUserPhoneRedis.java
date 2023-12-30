package com.socialuni.social.user.sdk.logic.redis;

import com.socialuni.social.common.api.constant.CommonRedisKey;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.user.sdk.model.DO.SocialUserPhoneDo;
import com.socialuni.social.user.sdk.repository.SocialUserPhoneRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialUserPhoneRedis {
    @Resource
    SocialUserPhoneRepository socialUserPhoneApi;

    public SocialUserPhoneDo findByPhoneNum(String phoneNum) {
//        return socialUserPhoneRepository.findByPhoneNumAndStatus(phoneNum, CommonStatus.enable);
        return socialUserPhoneApi.findByPhoneNumAndDevId(phoneNum, DevAccountFacade.getDevIdNotNull());
    }

    @Cacheable(cacheNames = CommonRedisKey.findUserPhoneByUserId, key = "#userId")
    public SocialUserPhoneDo findUserPhoneByUserId(Integer userId) {
//        return socialUserPhoneRepository.findByUserIdAndStatus(userId, CommonStatus.enable);
        return socialUserPhoneApi.findByUserId(userId);
    }
}