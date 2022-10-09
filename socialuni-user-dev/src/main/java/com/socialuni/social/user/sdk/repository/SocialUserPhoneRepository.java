package com.socialuni.social.user.sdk.repository;

import com.socialuni.social.common.constant.CommonRedisKey;
import com.socialuni.social.user.sdk.api.SocialUserPhoneApi;
import com.socialuni.social.user.sdk.entity.SocialUserPhoneDo;
import com.socialuni.social.user.sdk.model.SocialUserPhoneModel;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialUserPhoneRepository extends SocialUserPhoneApi,JpaRepository<SocialUserPhoneDo, Integer> {
    SocialUserPhoneModel findByPhoneNumAndStatus(String phoneNum, String status);

    SocialUserPhoneModel findByPhoneNumAndDevId(String phoneNum, Integer devId);

    SocialUserPhoneModel findByUserId(Integer userId);

    SocialUserPhoneModel findByUserIdAndStatus(Integer userId, String status);

    //关注后用户缓存修改，一人+粉丝，一人+关注
    @Caching(
            evict = {@CacheEvict(cacheNames = CommonRedisKey.userById, key = "#phoneDO.userId")},
            put = {@CachePut(cacheNames = CommonRedisKey.findUserPhoneByUserId, key = "#phoneDO.userId")}
    )
    SocialUserPhoneModel save(SocialUserPhoneModel phoneDO);
}