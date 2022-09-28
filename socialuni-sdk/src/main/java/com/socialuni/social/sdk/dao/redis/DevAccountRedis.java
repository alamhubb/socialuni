package com.socialuni.social.sdk.dao.redis;

import com.socialuni.social.tance.repository.DevAccountRepository;
import com.socialuni.social.tance.entity.DevAccountDO;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DevAccountRedis {
    @Resource
    private DevAccountRepository devAccountRepository;

    @Caching(
            put = {
                    @CachePut(cacheNames = "getDevAccountById", key = "#devAccount.id"),
                    @CachePut(cacheNames = "getDevAccountBySecretKey", key = "#devAccount.secretKey", condition="#devAccount.secretKey!=null"),
                    @CachePut(cacheNames = "getDevAccountBySocialuniId", key = "#devAccount.socialuniId")
            }
    )
    public DevAccountDO saveDevAccount(DevAccountDO devAccount) {
        return devAccountRepository.save(devAccount);
    }
}
