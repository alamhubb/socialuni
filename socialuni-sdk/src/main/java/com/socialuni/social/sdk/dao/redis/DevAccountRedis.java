package com.socialuni.social.sdk.dao.redis;

import com.socialuni.social.tance.sdk.api.DevAccountInterface;
import com.socialuni.social.tance.sdk.model.DevAccountModel;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DevAccountRedis {
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
        return devAccountApi.savePut(devAccount);
    }
}
