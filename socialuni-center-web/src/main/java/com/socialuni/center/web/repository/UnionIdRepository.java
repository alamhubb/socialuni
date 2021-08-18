package com.socialuni.center.web.repository;

import com.socialuni.center.web.model.DO.UnionIdDO;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.UniqueConstraint;
import java.util.Date;
import java.util.Optional;

/**
 * @author qinkaiyuan
 * @since 1.0.0
 */
public interface UnionIdRepository extends JpaRepository<UnionIdDO, Long> {
    @Caching(
            put = {
                    @CachePut(cacheNames = "getUnionId", key = "#unionIdDO.unionId"),
                    @CachePut(cacheNames = "getUnionIdByCom", key = "#unionIdDO.contentType+'-'+#unionIdDO.contentId+'-'+#unionIdDO.status+'-'+#unionIdDO.devId+'-'+#unionIdDO.userId+'-'+#unionIdDO.beginTime")
            }
    )
    UnionIdDO save(UnionIdDO unionIdDO);

    @Cacheable(cacheNames = "getUnionId", key = "#p0")
    Optional<UnionIdDO> findFirstByUnionIdOrderByIdDesc(String unionId);

    //查询这条动态，是否有可用的unionid
    @Cacheable(cacheNames = "getUnionIdByCom", key = "#contentType+'-'+#contentId+'-'+#status+'-'+#devId+'-'+#userId+'-'+#beginTime")
    UnionIdDO findFirstByContentTypeAndContentIdAndStatusAndDevIdAndUserIdAndBeginTimeOrderByIdDesc(String contentType, Integer contentId, String status, Integer devId, Integer userId, Date beginTime);

    //查询这条unionid是否还处于可用状态
}