package com.socialuni.social.tag.dao.repository;


import cn.hutool.core.bean.BeanUtil;
import com.socialuni.social.tag.dao.DO.SocialuniTagTypeDO;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SocialuniTagTypeRepository extends JpaRepository<SocialuniTagTypeDO, Integer> {
    @Caching(
            put = {
                    @CachePut(cacheNames = "tagTypeByName", key = "#tagTypeDO.name"),
                    @CachePut(cacheNames = "tagTypeById", key = "#tagTypeDO.id"),
            }
    )
    default SocialuniTagTypeDO savePut(SocialuniTagTypeDO tagTypeDO){
        return this.save(BeanUtil.toBean(tagTypeDO, SocialuniTagTypeDO.class));
    }

    @Cacheable(cacheNames = "tagTypeByName", key = "#name")
    SocialuniTagTypeDO findFirstByName(String name);

    @Cacheable(cacheNames = "tagTypeById", key = "#id")
    Optional<SocialuniTagTypeDO> findById(Integer id);

    List<?  extends SocialuniTagTypeDO> findByStatusOrderByOrderLevelDescTalkCountDesc(String status);
}