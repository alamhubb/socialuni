package com.socialuni.social.user.sdk.repository;

import cn.hutool.core.bean.BeanUtil;
import com.socialuni.social.common.api.constant.CommonRedisKey;
import com.socialuni.social.user.sdk.model.DO.SocialUserPhoneDo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialUserPhoneRepository extends JpaRepository<SocialUserPhoneDo, Integer> {
    SocialUserPhoneDo findByPhoneNumAndStatus(String phoneNum, String status);

    SocialUserPhoneDo findByPhoneNumAndDevId(String phoneNum, Integer devId);

    SocialUserPhoneDo findByUserId(Integer userId);

    SocialUserPhoneDo findByUserIdAndStatus(Integer userId, String status);

    //关注后用户缓存修改，一人+粉丝，一人+关注
    @Caching(
            evict = {@CacheEvict(cacheNames = CommonRedisKey.userById, key = "#phoneDO.userId")},
            put = {@CachePut(cacheNames = CommonRedisKey.findUserPhoneByUserId, key = "#phoneDO.userId")}
    )
    default SocialUserPhoneDo savePut(SocialUserPhoneDo phoneDO){
        return this.save(BeanUtil.toBean(phoneDO,SocialUserPhoneDo.class));
    }
}