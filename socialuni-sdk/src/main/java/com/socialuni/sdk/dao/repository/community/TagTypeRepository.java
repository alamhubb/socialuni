package com.socialuni.sdk.dao.repository.community;


import com.socialuni.sdk.model.DO.tag.TagTypeDO;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagTypeRepository extends JpaRepository<TagTypeDO, Integer> {
    @Caching(
            put = {
                    @CachePut(cacheNames = "tagTypeByName", key = "#tagTypeDO.name"),
                    @CachePut(cacheNames = "tagTypeById", key = "#tagTypeDO.id"),
            }
    )
    TagTypeDO save(TagTypeDO tagTypeDO);

    @Cacheable(cacheNames = "tagTypeByName", key = "#name")
    TagTypeDO findFirstByName(String name);

    @Cacheable(cacheNames = "tagTypeById", key = "#id")
    Optional<TagTypeDO> findById(Integer id);

    List<TagTypeDO> findByStatusAndTalkCountGreaterThanOrderByOrderLevelDescTalkCountDesc(String status, Integer zeroCount);
}