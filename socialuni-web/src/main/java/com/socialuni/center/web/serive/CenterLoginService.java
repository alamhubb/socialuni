package com.socialuni.center.web.serive;

import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.center.web.domain.thirdUser.AuthThirdUserDomain;
import com.socialuni.center.web.model.DO.DevAccountDO;
import com.socialuni.center.web.utils.DevAccountUtils;
import com.socialuni.social.sdk.constant.AuthType;
import com.socialuni.social.sdk.constant.ProviderLoginType;
import com.socialuni.social.sdk.domain.login.SocialProviderLoginDomain;
import com.socialuni.social.sdk.exception.SocialParamsException;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.model.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.social.model.model.RO.user.login.SocialLoginRO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@Slf4j
public class CenterLoginService {
    @Resource
    private AuthThirdUserDomain authThirdUserDomain;
    @Resource
    private SocialProviderLoginDomain socialProviderLoginDomain;

    //因旧版本原因合在了一起，新版本要拆开
    @Transactional
    public SocialLoginRO<CenterMineUserDetailRO> providerLogin(SocialProviderLoginQO loginQO) {
        DevAccountDO devAccountDO = DevAccountUtils.getDevAccount();
        // 只有清池支持渠道登录
        if (!devAccountDO.getId().equals(1)) {
            // 其他的只支持社交联盟登陆
            if (!ProviderLoginType.socialuni.equals(loginQO.getProvider())) {
                throw new SocialParamsException("仅支持社交联盟登录");
            }
        }
        SocialLoginRO<SocialMineUserDetailRO> socialLoginRO = socialProviderLoginDomain.providerLogin(loginQO);

        UserDO mineUser = SocialUserUtil.get(socialLoginRO.getUser().getId());

        //中心授权
        SocialLoginRO<CenterMineUserDetailRO> centerLoginRO = authThirdUserDomain.authThirdUser(mineUser, AuthType.user);
        return centerLoginRO;
    }
}
