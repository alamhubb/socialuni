package com.socialuni.social.user.sdk.repository;

import cn.hutool.core.bean.BeanUtil;
import com.socialuni.social.user.sdk.api.SocialuniUserSocialCoinInterface;
import com.socialuni.social.user.sdk.entity.SocialuniUserSocialCoinDo;
import com.socialuni.social.user.sdk.model.SocialuniUserSocialCoinModel;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialuniUserSocialCoinRepository extends SocialuniUserSocialCoinInterface,JpaRepository<SocialuniUserSocialCoinDo, Integer> {
    @Cacheable(cacheNames = "userSocialCoinByUserId", key = "#userId")
    SocialuniUserSocialCoinModel findByUserId(Integer userId);

    @Caching(
            put = {@CachePut(cacheNames = "userSocialCoinByUserId", key = "#socialuniUserExpandDO.userId")}
    )
    default SocialuniUserSocialCoinModel savePut(SocialuniUserSocialCoinModel socialuniUserExpandDO){
        return this.save(BeanUtil.toBean(socialuniUserExpandDO,SocialuniUserSocialCoinDo.class));
    }

}

