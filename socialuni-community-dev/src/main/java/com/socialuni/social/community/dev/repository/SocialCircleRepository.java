package com.socialuni.social.community.dev.repository;

import cn.hutool.core.bean.BeanUtil;
import com.socialuni.social.community.dev.entity.SocialuniCircleDO;
import com.socialuni.social.community.sdk.api.SocialCircleApi;
import com.socialuni.social.community.sdk.enumeration.CircleRedisKey;
import com.socialuni.social.community.sdk.model.SocialuniCircleModel;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SocialCircleRepository extends SocialCircleApi,JpaRepository<SocialuniCircleDO, Integer> {
    @Caching(
            put = {
                    @CachePut(cacheNames = CircleRedisKey.tagByName, key = "#tagDO.name"),
            }
    )
    default SocialuniCircleModel savePut(SocialuniCircleModel tagDO){
        return this.save(BeanUtil.toBean(tagDO,SocialuniCircleDO.class));
    }

    //查询热门前10tag
    List<?  extends SocialuniCircleModel> findByStatusOrderByCountDesc(String status, Pageable pageable);
    @Cacheable(cacheNames = "getCircleInfoByName", key = "#name")
    SocialuniCircleModel findFirstByNameAndStatus(String name, String status);

    @Cacheable(cacheNames = "getCircleInfo", key = "#circleId")
    SocialuniCircleModel findFirstByIdAndStatus(Integer circleId, String status);

    List<?  extends SocialuniCircleModel> findByStatusAndVisibleGenderOrderByCountDesc(String status, String gender);

    //获取tagTYpe所有子tag
    @Cacheable(cacheNames = "getCirclesByType", key = "#tagTypeId")
    List<?  extends SocialuniCircleModel> findByTagTypeIdAndStatusOrderByCountDesc(Integer tagTypeId, String status);

    List<?  extends SocialuniCircleModel> findByTagTypeIdAndStatusAndVisibleGenderOrderByCountDesc(Integer tagTypeId, String status, String gender);
}
