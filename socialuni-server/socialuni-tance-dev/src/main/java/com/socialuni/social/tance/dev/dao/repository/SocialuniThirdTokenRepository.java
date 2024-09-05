package com.socialuni.social.tance.dev.dao.repository;

import com.socialuni.social.common.api.constant.CommonRedisKey;
import com.socialuni.social.tance.dev.api.DevAccountInterface;
import com.socialuni.social.tance.dev.dao.DO.DevAccountDo;
import com.socialuni.social.tance.dev.dao.DO.SocialuniThirdTokenDO;
import com.socialuni.social.tance.dev.model.DevAccountModel;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


/**
 * @author qinkaiyuan
 * @since 1.0.0
 */
public interface SocialuniThirdTokenRepository extends JpaRepository<SocialuniThirdTokenDO, Integer> {
    @Cacheable(cacheNames = "findSocialuniThirdTokenByUserId", key = "#userId")
    SocialuniThirdTokenDO findFirstByUserId(Long userId);

    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "findSocialuniThirdTokenByUserId", key = "#thirdTokenDO.userId")
            },
            put = {
                    @CachePut(cacheNames ="findSocialuniThirdTokenByUserId", key = "#thirdTokenDO.userId")
            }
    )
    default SocialuniThirdTokenDO savePut(SocialuniThirdTokenDO thirdTokenDO) {
        return this.save(thirdTokenDO);
    }
}