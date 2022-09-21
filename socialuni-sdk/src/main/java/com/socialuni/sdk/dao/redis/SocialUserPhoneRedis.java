package com.socialuni.sdk.dao.redis;

import com.socialuni.sdk.dao.redis.redisKey.CommonRedisKey;
import com.socialuni.sdk.dao.DO.user.SocialUserPhoneDO;
import com.socialuni.sdk.dao.repository.user.SocialUserPhoneRepository;
import com.socialuni.sdk.utils.DevAccountUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialUserPhoneRedis {
    @Resource
    SocialUserPhoneRepository socialUserPhoneRepository;

    public SocialUserPhoneDO findByPhoneNum(String phoneNum) {
//        return socialUserPhoneRepository.findByPhoneNumAndStatus(phoneNum, CommonStatus.enable);
        return socialUserPhoneRepository.findByPhoneNumAndDevId(phoneNum, DevAccountUtils.getDevIdNotNull());
    }

    @Cacheable(cacheNames = CommonRedisKey.findUserPhoneByUserId, key = "#userId")
    public SocialUserPhoneDO findUserPhoneByUserId(Integer userId) {
//        return socialUserPhoneRepository.findByUserIdAndStatus(userId, CommonStatus.enable);
        return socialUserPhoneRepository.findByUserId(userId);
    }
}