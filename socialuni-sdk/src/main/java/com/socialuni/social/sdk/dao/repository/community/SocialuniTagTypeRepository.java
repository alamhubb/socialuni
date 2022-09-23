package com.socialuni.social.sdk.dao.repository.community;


import com.socialuni.social.sdk.dao.DO.tag.SocialuniTagTypeDO;
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
    SocialuniTagTypeDO save(SocialuniTagTypeDO tagTypeDO);

    @Cacheable(cacheNames = "tagTypeByName", key = "#name")
    SocialuniTagTypeDO findFirstByName(String name);

    @Cacheable(cacheNames = "tagTypeById", key = "#id")
    Optional<SocialuniTagTypeDO> findById(Integer id);

    List<SocialuniTagTypeDO> findByStatusOrderByOrderLevelDescTalkCountDesc(String status);
}