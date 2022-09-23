package com.socialuni.social.sdk.dao.repository.community;

import com.socialuni.social.sdk.dao.DO.circle.SocialuniCircleDO;
import com.socialuni.social.sdk.dao.redis.redisKey.CircleRedisKey;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SocialCircleRepository extends JpaRepository<SocialuniCircleDO, Integer> {
    @Caching(
            put = {
                    @CachePut(cacheNames = CircleRedisKey.tagByName, key = "#tagDO.name"),
            }
    )
    SocialuniCircleDO save(SocialuniCircleDO tagDO);

    //查询热门前10tag
    List<SocialuniCircleDO> findByStatusOrderByCountDesc(String status, Pageable pageable);
    @Cacheable(cacheNames = "getCircleInfoByName", key = "#name")
    SocialuniCircleDO findFirstByNameAndStatus(String name, String status);

    @Cacheable(cacheNames = "getCircleInfo", key = "#circleId")
    SocialuniCircleDO findFirstByIdAndStatus(Integer circleId, String status);

    List<SocialuniCircleDO> findByStatusAndVisibleGenderOrderByCountDesc(String status, String gender);

    //获取tagTYpe所有子tag
    @Cacheable(cacheNames = "getCirclesByType", key = "#tagTypeId")
    List<SocialuniCircleDO> findByTagTypeIdAndStatusOrderByCountDesc(Integer tagTypeId, String status);

    List<SocialuniCircleDO> findByTagTypeIdAndStatusAndVisibleGenderOrderByCountDesc(Integer tagTypeId, String status, String gender);
}
