package com.socialuni.social.sdk.redis;

import com.socialuni.social.entity.model.DO.user.SocialUserPhoneDO;
import com.socialuni.social.sdk.redis.RedisKeysConst;
import com.socialuni.social.sdk.repository.SocialUserPhoneRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialUserPhoneRedis {
    @Resource
    SocialUserPhoneRepository socialUserPhoneRepository;

    public SocialUserPhoneDO findByPhoneNum(String phoneNum) {
//        return socialUserPhoneRepository.findByPhoneNumAndStatus(phoneNum, CommonStatus.enable);
        return socialUserPhoneRepository.findByPhoneNum(phoneNum);
    }

    @Cacheable(cacheNames = RedisKeysConst.findUserPhoneByUserId, key = "#userId")
    public SocialUserPhoneDO findUserPhoneByUserId(Integer userId) {
//        return socialUserPhoneRepository.findByUserIdAndStatus(userId, CommonStatus.enable);
        return socialUserPhoneRepository.findByUserId(userId);
    }
}