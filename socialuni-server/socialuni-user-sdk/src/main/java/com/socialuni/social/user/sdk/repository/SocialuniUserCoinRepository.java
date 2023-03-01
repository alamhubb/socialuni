package com.socialuni.social.user.sdk.repository;

import cn.hutool.core.bean.BeanUtil;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserCoinDo;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialuniUserCoinRepository extends JpaRepository<SocialuniUserCoinDo, Integer> {
    @Cacheable(cacheNames = "userSocialCoinByUserId", key = "#userId")
    SocialuniUserCoinDo findByUserId(Integer userId);

    @Caching(
            put = {@CachePut(cacheNames = "userSocialCoinByUserId", key = "#socialuniUserExpandDO.userId")}
    )
    default SocialuniUserCoinDo savePut(SocialuniUserCoinDo socialuniUserExpandDO){
        return this.save(BeanUtil.toBean(socialuniUserExpandDO, SocialuniUserCoinDo.class));
    }

}

