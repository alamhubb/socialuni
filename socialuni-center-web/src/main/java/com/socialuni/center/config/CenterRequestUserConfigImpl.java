package com.socialuni.center.config;

import com.socialuni.center.qingchi.QingchiTokenDO;
import com.socialuni.center.qingchi.QingchiTokenRepository;
import com.socialuni.center.qingchi.QingchiUserAccountDO;
import com.socialuni.center.qingchi.QingchiUserAccountRepository;
import com.socialuni.sdk.config.SocialRequestUserConfig;
import com.socialuni.sdk.config.SocialRequestUserConfigDefaultImpl;
import com.socialuni.sdk.config.SocialTokenUtil;
import com.socialuni.sdk.constant.SocialuniAccountProviderType;
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
    @Resource
    QingchiTokenRepository qingchiTokenRepository;
    @Resource
    QingchiUserAccountRepository qingchiUserAccountRepository;

    @Override
    public String getToken() {
        String token = super.getToken();
        QingchiTokenDO qingchiTokenDO = qingchiTokenRepository.findOneByToken(token);
        if (qingchiTokenDO != null) {
            QingchiUserAccountDO qingchiUserAccountDO = qingchiUserAccountRepository.findOneByUserIdAndProvider(qingchiTokenDO.getUserId(), SocialuniAccountProviderType.socialuni);
            if (qingchiUserAccountDO != null) {
                return qingchiUserAccountDO.getSessionKey();
            }
        }
        return token;
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
        //三方应用传的可以直接传用户id作为token
        return UnionIdDbUtil.getUserUnionIdByUidNotNull(token);
    }
}
