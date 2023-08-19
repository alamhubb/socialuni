package com.socialuni.social.user.sdk.repository;

import cn.hutool.core.bean.BeanUtil;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserExpandDo;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserOpenIdDO;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface SocialuniUserOpenIdRepository extends JpaRepository<SocialuniUserOpenIdDO, Integer> {
    @Cacheable(cacheNames = "userOpenIdByUserId", key = "#userId")
    SocialuniUserOpenIdDO findByUserId(Integer userId);

    @Cacheable(cacheNames = "userOpenIdByUserOpenId", key = "#userOpenId")
    SocialuniUserOpenIdDO findByUserOpenId(String userOpenId);

    @Caching(
            put = {
                    @CachePut(cacheNames = "userOpenIdByUserId", key = "#socialuniUserOpenIdDO.userId"),
                    @CachePut(cacheNames = "userOpenIdByUserOpenId", key = "#socialuniUserOpenIdDO.userOpenId"),
            }
    )
    SocialuniUserOpenIdDO save(SocialuniUserOpenIdDO socialuniUserOpenIdDO);
}

