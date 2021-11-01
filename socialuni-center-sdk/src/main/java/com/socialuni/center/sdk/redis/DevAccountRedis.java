package com.socialuni.center.sdk.redis;

import com.socialuni.center.sdk.repository.DevAccountRepository;
import com.socialuni.center.sdk.model.DevAccountDO;
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
                    @CachePut(cacheNames = "getDevAccountBySecretKey", key = "#devAccount.secretKey", condition="#devAccount.secretKey!=null" )
            }
    )
    public DevAccountDO saveDevAccount(DevAccountDO devAccount) {
        return devAccountRepository.save(devAccount);
    }
}
