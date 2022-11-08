package com.socialuni.social.user.sdk.repository;

import cn.hutool.core.bean.BeanUtil;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserExpandDo;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialuniUserExpandRepository extends JpaRepository<SocialuniUserExpandDo, Integer> {
    @Cacheable(cacheNames = "userExpandByUserId", key = "#userId")
    SocialuniUserExpandDo findByUserId(Integer userId);

    @Caching(
            put = {@CachePut(cacheNames = "userExpandByUserId", key = "#SocialuniUserExpandDo.userId")}
    )
    default SocialuniUserExpandDo savePut(SocialuniUserExpandDo SocialuniUserExpandDo){
        return this.save(BeanUtil.toBean(SocialuniUserExpandDo,SocialuniUserExpandDo.class));
    }

}

