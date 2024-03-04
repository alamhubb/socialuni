package com.socialuni.social.common.sdk.dao.repository;

import com.socialuni.social.report.sdk.dao.DO.WhiteKeywordsDO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WhiteKeywordsRepository extends JpaRepository<WhiteKeywordsDO, Integer> {
    @Cacheable(cacheNames = "whiteKeywordsAll")
    List<WhiteKeywordsDO> findAllByStatus(String status);

    //新增一个清空所有，把当前的缓存
    @Caching(
            evict = {@CacheEvict(cacheNames = "whiteKeywordsAll")},
            put = {@CachePut(cacheNames = {"whiteKeywords"}, key = "#keywordsDO.id")}
    )
    WhiteKeywordsDO save(WhiteKeywordsDO keywordsDO);
}

