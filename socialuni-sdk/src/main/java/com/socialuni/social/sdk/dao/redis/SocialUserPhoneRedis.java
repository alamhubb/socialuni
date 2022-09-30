package com.socialuni.social.sdk.dao.redis;

import com.socialuni.social.sdk.dao.redis.redisKey.CommonRedisKey;
import com.socialuni.social.sdk.dao.DO.user.SocialUserPhoneDO;
import com.socialuni.social.sdk.dao.repository.user.SocialUserPhoneRepository;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialUserPhoneRedis {
    @Resource
    SocialUserPhoneRepository socialUserPhoneRepository;

    public SocialUserPhoneDO findByPhoneNum(String phoneNum) {
//        return socialUserPhoneRepository.findByPhoneNumAndStatus(phoneNum, CommonStatus.enable);
        return socialUserPhoneRepository.findByPhoneNumAndDevId(phoneNum, DevAccountFacade.getDevIdNotNull());
    }

    @Cacheable(cacheNames = CommonRedisKey.findUserPhoneByUserId, key = "#userId")
    public SocialUserPhoneDO findUserPhoneByUserId(Integer userId) {
//        return socialUserPhoneRepository.findByUserIdAndStatus(userId, CommonStatus.enable);
        return socialUserPhoneRepository.findByUserId(userId);
    }
}