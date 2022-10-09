package com.socialuni.social.tance.sdk.cache;

import com.socialuni.social.common.facade.SocialuniRepositoryFacade;
import com.socialuni.social.tance.sdk.model.SocialuniUnionIdDO;
import com.socialuni.social.tance.sdk.repository.SocialuniUnionIdRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * @author qinkaiyuan
 * @since 1.0.0
 */
@Component
@Slf4j
public class SocialuniUnionIdCache {
    private static SocialuniUnionIdRepository uniContentUnionIdRepository;

    @Resource
    public void setUniContentUnionIdRepository(SocialuniUnionIdRepository uniContentUnionIdRepository) {
        SocialuniUnionIdCache.uniContentUnionIdRepository = uniContentUnionIdRepository;
    }

    //新增一个清空所有，把当前的缓存
    @Caching(
            evict = {@CacheEvict(cacheNames = "getUnionIdByUuId")},
            put = {@CachePut(cacheNames = {"getUnionIdById"}, key = "#uniContentUnionIdDO.id")}
    )
    public SocialuniUnionIdDO save(SocialuniUnionIdDO uniContentUnionIdDO) {
        return SocialuniRepositoryFacade.save(uniContentUnionIdDO);
    }

    @Cacheable(cacheNames = "getUnionIdById", key = "#unionId")
    public SocialuniUnionIdDO findById(Integer unionId) {
        return SocialuniRepositoryFacade.findById(unionId, SocialuniUnionIdDO.class);
    }

    @Cacheable(cacheNames = "getUnionIdByUuId", key = "#uuid")
    public SocialuniUnionIdDO findByUuId(String uuid) {
        return uniContentUnionIdRepository.findByUuid(uuid);
    }
}