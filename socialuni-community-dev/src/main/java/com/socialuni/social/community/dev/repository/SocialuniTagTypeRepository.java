package com.socialuni.social.community.dev.repository;


import cn.hutool.core.bean.BeanUtil;
import com.socialuni.social.community.dev.entity.SocialuniTagTypeDO;
import com.socialuni.social.community.sdk.api.SocialuniTagTypeApi;
import com.socialuni.social.community.sdk.model.SocialuniTagTypeModel;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SocialuniTagTypeRepository extends SocialuniTagTypeApi, JpaRepository<SocialuniTagTypeDO, Integer> {
    @Caching(
            put = {
                    @CachePut(cacheNames = "tagTypeByName", key = "#tagTypeDO.name"),
                    @CachePut(cacheNames = "tagTypeById", key = "#tagTypeDO.id"),
            }
    )
    default SocialuniTagTypeModel savePut(SocialuniTagTypeModel tagTypeDO){
        return this.save(BeanUtil.toBean(tagTypeDO,SocialuniTagTypeDO.class));
    }

    @Cacheable(cacheNames = "tagTypeByName", key = "#name")
    SocialuniTagTypeModel findFirstByName(String name);

    @Cacheable(cacheNames = "tagTypeById", key = "#id")
    Optional<SocialuniTagTypeDO> findById(Integer id);

    List<?  extends SocialuniTagTypeModel> findByStatusOrderByOrderLevelDescTalkCountDesc(String status);
}