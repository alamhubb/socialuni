package com.socialuni.center.config;

import com.socialuni.sdk.config.SocialRequestUserConfig;
import com.socialuni.sdk.config.SocialRequestUserConfigDefaultImpl;
import com.socialuni.sdk.config.SocialTokenUtil;
import com.socialuni.sdk.model.DO.user.SocialTokenDO;
import com.socialuni.sdk.repository.CommonTokenRepository;
import com.socialuni.sdk.utils.DevAccountUtils;
import com.socialuni.sdk.utils.UnionIdDbUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CenterRequestUserConfigImpl extends SocialRequestUserConfigDefaultImpl implements SocialRequestUserConfig {

    @Resource
    CommonTokenRepository commonTokenRepository;


    @Override
    public String getToken() {
        return super.getToken();
    }

    @Override
    public Integer getUserId() {
        Integer devId = DevAccountUtils.getDevIdNotNull();
        if (devId == 1) {
            return super.getUserId();
        }
        String token = SocialTokenUtil.getToken();
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        SocialTokenDO socialTokenDO = commonTokenRepository.findOneByToken(token);
        if (socialTokenDO != null) {
            return socialTokenDO.getUserId();
        }
        return UnionIdDbUtil.getUserUnionIdByUidNotNull(token);
    }
}
