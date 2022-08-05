package com.socialuni.center.web.redis;

import com.socialuni.center.web.redis.redisKey.RedisKeysConst;
import com.socialuni.social.entity.model.DO.user.SocialUserPhoneDO;
import com.socialuni.center.web.repository.user.SocialUserPhoneRepository;
import com.socialuni.center.web.utils.DevAccountUtils;
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

    @Cacheable(cacheNames = RedisKeysConst.findUserPhoneByUserId, key = "#userId")
    public SocialUserPhoneDO findUserPhoneByUserId(Integer userId) {
//        return socialUserPhoneRepository.findByUserIdAndStatus(userId, CommonStatus.enable);
        return socialUserPhoneRepository.findByUserId(userId);
    }
}