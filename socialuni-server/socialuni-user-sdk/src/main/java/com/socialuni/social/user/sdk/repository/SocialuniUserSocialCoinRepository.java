package com.socialuni.social.user.sdk.repository;

import cn.hutool.core.bean.BeanUtil;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserSocialCoinDo;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialuniUserSocialCoinRepository extends JpaRepository<SocialuniUserSocialCoinDo, Integer> {
    @Cacheable(cacheNames = "userSocialCoinByUserId", key = "#userId")
    SocialuniUserSocialCoinDo findByUserId(Integer userId);

    @Caching(
            put = {@CachePut(cacheNames = "userSocialCoinByUserId", key = "#socialuniUserExpandDO.userId")}
    )
    default SocialuniUserSocialCoinDo savePut(SocialuniUserSocialCoinDo socialuniUserExpandDO){
        return this.save(BeanUtil.toBean(socialuniUserExpandDO,SocialuniUserSocialCoinDo.class));
    }

}

