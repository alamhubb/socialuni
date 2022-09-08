package com.socialuni.sdk.dao.repository;

import com.socialuni.sdk.dao.DO.keywords.CustomKeywordsDO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomKeywordsRepository extends JpaRepository<CustomKeywordsDO, Integer> {
    @Cacheable(cacheNames = "customKeywordsAll")
    List<CustomKeywordsDO> findAllByStatus(String status);

    //新增一个清空所有，把当前的缓存
    @Caching(
            evict = {@CacheEvict(cacheNames = "customKeywordsAll")},
            put = {@CachePut(cacheNames = {"customKeywords"}, key = "#keywordsDO.id")}
    )
    CustomKeywordsDO save(CustomKeywordsDO keywordsDO);
}

