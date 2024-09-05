package com.socialuni.social.tance.dev.cache;

import com.socialuni.social.tance.dev.api.DevAccountInterface;
import com.socialuni.social.tance.dev.api.DevAccountRedisInterface;
import com.socialuni.social.tance.dev.model.DevAccountModel;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DevAccountRedis implements DevAccountRedisInterface {
    @Resource
    private DevAccountInterface devAccountApi;

    @Caching(
            put = {
                    @CachePut(cacheNames = "getDevAccountById", key = "#devAccount.id"),
                    @CachePut(cacheNames = "getDevAccountBySecretKey", key = "#devAccount.secretKey", condition="#devAccount.secretKey!=null"),
                    @CachePut(cacheNames = "getDevAccountBySocialuniId", key = "#devAccount.socialuniId")
            }
    )
    public DevAccountModel saveDevAccount(DevAccountModel devAccount) {
        return devAccountApi.save(devAccount);
    }
}
