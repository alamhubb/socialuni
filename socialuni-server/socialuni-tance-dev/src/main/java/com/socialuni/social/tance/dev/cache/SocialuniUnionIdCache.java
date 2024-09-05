package com.socialuni.social.tance.dev.cache;

import cn.hutool.core.bean.BeanUtil;
import com.socialuni.social.tance.dev.repository.SocialuniUnionIdRepository;
import com.socialuni.social.tance.dev.entity.SocialuniUnionIdDo;
import com.socialuni.social.tance.dev.api.SocialuniUnionIdInterface;
import com.socialuni.social.tance.dev.model.SocialuniUnionIdModler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author qinkaiyuan
 * @since 1.0.0
 */
@Component
@Slf4j
public class SocialuniUnionIdCache implements SocialuniUnionIdInterface {
    private static SocialuniUnionIdRepository uniContentUnionIdRepository;

    @Resource
    public void setUniContentUnionIdRepository(SocialuniUnionIdRepository uniContentUnionIdRepository) {
        SocialuniUnionIdCache.uniContentUnionIdRepository = uniContentUnionIdRepository;
    }

    //新增一个清空所有，把当前的缓存
    @Override
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "getUnionIdByUuId", key = "#uniContentUnionIdDO.uuid"),
            },
            put = {@CachePut(cacheNames = {"getUnionIdByUnionId"}, key = "#uniContentUnionIdDO.id", condition = "#uniContentUnionIdDO.id != null")}
    )
    public SocialuniUnionIdDo savePut(SocialuniUnionIdModler uniContentUnionIdDO) {
        return uniContentUnionIdRepository.save(BeanUtil.copyProperties(uniContentUnionIdDO, SocialuniUnionIdDo.class));
    }

    @Override
    @Cacheable(cacheNames = "getUnionIdByUnionId", key = "#unionId")
    public SocialuniUnionIdDo findByUnionId(Long unionId) {
        return uniContentUnionIdRepository.findFirstByUnionId(unionId);
    }

    @Override
    @Cacheable(cacheNames = "getUnionIdByUuId", key = "#uuid")
    public SocialuniUnionIdDo findByUuId(String uuid) {
        return uniContentUnionIdRepository.findByUuid(uuid);
    }

    @Override
    public List<String> findUuidAllByContentType(String contentTyp){
        return uniContentUnionIdRepository.findAllUuidByContentType(contentTyp);
    }

    @Override
    public List<Long> findAllUnionIdsByContentType(String contentTyp) {
        return uniContentUnionIdRepository.findAllUnionIdsByContentType(contentTyp);
    }
}