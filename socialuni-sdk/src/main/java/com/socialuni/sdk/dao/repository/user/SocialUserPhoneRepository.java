package com.socialuni.sdk.dao.repository.user;

import com.socialuni.sdk.dao.redis.redisKey.CommonRedisKey;
import com.socialuni.sdk.dao.DO.user.SocialUserPhoneDO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialUserPhoneRepository extends JpaRepository<SocialUserPhoneDO, Integer> {
    SocialUserPhoneDO findByPhoneNumAndStatus(String phoneNum, String status);

    SocialUserPhoneDO findByPhoneNumAndDevId(String phoneNum, Integer devId);

    SocialUserPhoneDO findByUserId(Integer userId);

    SocialUserPhoneDO findByUserIdAndStatus(Integer userId, String status);

    //关注后用户缓存修改，一人+粉丝，一人+关注
    @Caching(
            evict = {@CacheEvict(cacheNames = CommonRedisKey.userById, key = "#phoneDO.userId")},
            put = {@CachePut(cacheNames = CommonRedisKey.findUserPhoneByUserId, key = "#phoneDO.userId")}
    )
    SocialUserPhoneDO save(SocialUserPhoneDO phoneDO);
}