package com.socialuni.social.tance.cache;

import com.socialuni.social.common.facade.SocialuniRepositoryFacade;
import com.socialuni.social.tance.sdk.model.SocialuniUnionIdModler;
import com.socialuni.social.tance.repository.SocialuniUnionIdRepository;
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
public class SocialuniUnionIdCache implements com.socialuni.social.tance.sdk.api.SocialuniUnionIdApi {
    private static SocialuniUnionIdRepository uniContentUnionIdRepository;

    @Resource
    public void setUniContentUnionIdRepository(SocialuniUnionIdRepository uniContentUnionIdRepository) {
        SocialuniUnionIdCache.uniContentUnionIdRepository = uniContentUnionIdRepository;
    }

    //新增一个清空所有，把当前的缓存
    @Override
    @Caching(
            evict = {@CacheEvict(cacheNames = "getUnionIdByUuId")},
            put = {@CachePut(cacheNames = {"getUnionIdById"}, key = "#uniContentUnionIdDO.id")}
    )
    public SocialuniUnionIdModler save(SocialuniUnionIdModler uniContentUnionIdDO) {
        return SocialuniRepositoryFacade.save(uniContentUnionIdDO);
    }

    @Override
    @Cacheable(cacheNames = "getUnionIdById", key = "#unionId")
    public SocialuniUnionIdModler findById(Integer unionId) {
        return SocialuniRepositoryFacade.findById(unionId, SocialuniUnionIdModler.class);
    }

    @Override
    @Cacheable(cacheNames = "getUnionIdByUuId", key = "#uuid")
    public SocialuniUnionIdModler findByUuId(String uuid) {
        return uniContentUnionIdRepository.findByUuid(uuid);
    }
}