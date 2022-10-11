package com.socialuni.social.user.sdk.repository;

import cn.hutool.core.bean.BeanUtil;
import com.socialuni.social.user.sdk.api.SocialuniUserExpandApi;
import com.socialuni.social.user.sdk.entity.SocialuniUserExpandDo;
import com.socialuni.social.user.sdk.model.SocialuniUserExpandModel;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialuniUserExpandRepository extends SocialuniUserExpandApi,JpaRepository<SocialuniUserExpandDo, Integer> {
    @Cacheable(cacheNames = "userExpandByUserId", key = "#userId")
    SocialuniUserExpandModel findByUserId(Integer userId);

    @Caching(
            put = {@CachePut(cacheNames = "userExpandByUserId", key = "#socialuniUserExpandModel.userId")}
    )
    default SocialuniUserExpandModel savePut(SocialuniUserExpandModel socialuniUserExpandModel){
        return this.save(BeanUtil.toBean(socialuniUserExpandModel,SocialuniUserExpandDo.class));
    }

}

