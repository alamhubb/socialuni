package com.socialuni.social.sdk.utils;

import com.socialuni.social.sdk.dao.redis.DistrictRedis;
import com.socialuni.social.sdk.dao.DO.DistrictDO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

@Component
public class DistrictStoreUtils {
    private static DistrictRedis districtRedis;

    @Resource
    public void setDistrictRedis(DistrictRedis districtRedis) {
        DistrictStoreUtils.districtRedis = districtRedis;
    }

    public static DistrictDO findFirstOneByAdCode(String adCode) {
        Optional<DistrictDO> optionalDistrictDO = districtRedis.findFirstOneByAdCode(adCode);
        return optionalDistrictDO.orElse(null);
    }

}