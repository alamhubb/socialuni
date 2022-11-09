package com.socialuni.social.community.sdk.repository;

import cn.hutool.core.bean.BeanUtil;
import com.socialuni.social.community.sdk.entity.SocialuniCircleDO;
import com.socialuni.social.community.sdk.enumeration.CircleRedisKey;
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
    default SocialuniCircleDO savePut(SocialuniCircleDO tagDO){
        return this.save(BeanUtil.toBean(tagDO,SocialuniCircleDO.class));
    }

    //查询热门前10tag
    List<?  extends SocialuniCircleDO> findByStatusOrderByCountDesc(String status, Pageable pageable);
    @Cacheable(cacheNames = "getCircleInfoByName", key = "#name")
    SocialuniCircleDO findFirstByNameAndStatus(String name, String status);

    @Cacheable(cacheNames = "getCircleInfo", key = "#circleId")
    SocialuniCircleDO findFirstByIdAndStatus(Integer circleId, String status);

    List<?  extends SocialuniCircleDO> findByStatusAndVisibleGenderOrderByCountDesc(String status, String gender);

    //获取tagTYpe所有子tag
    @Cacheable(cacheNames = "getCirclesByType", key = "#tagTypeId")
    List<?  extends SocialuniCircleDO> findByTagTypeIdAndStatusOrderByCountDesc(Integer tagTypeId, String status);

    List<?  extends SocialuniCircleDO> findByTagTypeIdAndStatusAndVisibleGenderOrderByCountDesc(Integer tagTypeId, String status, String gender);
}
