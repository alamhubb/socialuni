package com.socialuni.social.tance.dev.dao.repository;

import com.socialuni.social.tance.dev.dao.DO.SocialuniThirdTokenDO;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author qinkaiyuan
 * @since 1.0.0
 */
public interface SocialuniThirdTokenRepository extends JpaRepository<SocialuniThirdTokenDO, Integer> {
    @Cacheable(cacheNames = "findSocialuniThirdTokenByUserId", key = "#devId+'-'+#userId")
    SocialuniThirdTokenDO findFirstByDevIdAndUserId(Integer devId, Long userId);

    @Caching(
            put = {
                    @CachePut(cacheNames = "findSocialuniThirdTokenByUserId", key = "#thirdTokenDO.devId+'-'+#thirdTokenDO.userId")
            }
    )
    default SocialuniThirdTokenDO savePut(SocialuniThirdTokenDO thirdTokenDO) {
        return this.save(thirdTokenDO);
    }
}