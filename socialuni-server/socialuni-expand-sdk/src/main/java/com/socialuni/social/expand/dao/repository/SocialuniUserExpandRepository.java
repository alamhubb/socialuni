package com.socialuni.social.expand.dao.repository;

import cn.hutool.core.bean.BeanUtil;
import com.socialuni.social.expand.dao.DO.SocialuniUserExpandDo;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface SocialuniUserExpandRepository extends JpaRepository<SocialuniUserExpandDo, Integer> {
    @Cacheable(cacheNames = "userExpandByUserId", key = "#userId")
    SocialuniUserExpandDo findByUserId(Long userId);

    @Caching(
            put = {@CachePut(cacheNames = "userExpandByUserId", key = "#SocialuniUserExpandDo.userId")}
    )
    default SocialuniUserExpandDo savePut(SocialuniUserExpandDo SocialuniUserExpandDo){
        return this.save(BeanUtil.toBean(SocialuniUserExpandDo,SocialuniUserExpandDo.class));
    }


    @Query("select s.userId from SocialuniUserExpandDo s where s.openContactInfo = true and s.lastOnlineTime<:queryTime order by s.lastOnlineTime desc")
    List<Integer> findUserIdsByOpenContactInfoOrderByLastOnlineTimeDesc(Date queryTime);

}

