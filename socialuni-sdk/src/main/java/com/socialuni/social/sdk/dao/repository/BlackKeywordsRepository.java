package com.socialuni.social.sdk.dao.repository;

import com.socialuni.social.sdk.dao.DO.keywords.BlackKeywordsDO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlackKeywordsRepository extends JpaRepository<BlackKeywordsDO, Integer> {
    @Cacheable(cacheNames = "blackKeywordsAll")
    List<BlackKeywordsDO> findAllByStatus(String status);

    //新增一个清空所有，把当前的缓存
    @Caching(
            evict = {@CacheEvict(cacheNames = "blackKeywordsAll")},
            put = {@CachePut(cacheNames = {"blackKeywords"}, key = "#keywordsDO.id")}
    )
    BlackKeywordsDO save(BlackKeywordsDO keywordsDO);
}

