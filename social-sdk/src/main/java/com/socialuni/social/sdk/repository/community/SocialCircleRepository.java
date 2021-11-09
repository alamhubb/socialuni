package com.socialuni.social.sdk.repository.community;

import com.socialuni.social.entity.model.DO.circle.SocialCircleDO;
import com.socialuni.social.entity.model.DO.tag.TagDO;
import com.socialuni.social.sdk.redis.redisKey.TagRedisKey;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SocialCircleRepository extends JpaRepository<SocialCircleDO, Integer> {

    //查询热门前10tag
    List<SocialCircleDO> findByStatusOrderByCountDesc(String status, Pageable pageable);

}
