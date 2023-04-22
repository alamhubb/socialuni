package com.socialuni.social.community.sdk.utils;

import com.socialuni.social.community.sdk.dao.DO.SocialuniDistrictDO;
import com.socialuni.social.community.sdk.redis.DistrictRedis;
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

    public static SocialuniDistrictDO findFirstOneByAdCode(String adCode) {
        Optional<SocialuniDistrictDO> optionalDistrictDO = districtRedis.findFirstOneByAdCode(adCode);
        return optionalDistrictDO.orElse(null);
    }

}