package com.socialuni.center.web.serive;

import com.socialuni.center.web.domain.thirdUser.AuthThirdUserDomain;
import com.socialuni.social.sdk.domain.login.SocialProviderLoginDomain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class CenterLoginService {
    @Resource
    private AuthThirdUserDomain authThirdUserDomain;
    @Resource
    private SocialProviderLoginDomain socialProviderLoginDomain;

    //这个接口好像用不到，只会走三方授权，不需要社交联盟登录
    /*@Transactional
    public ResultRO<SocialLoginRO<CenterMineUserDetailRO>> providerLogin(SocialProviderLoginQO loginQO) {
        // 只有清池支持渠道登录
        // 其他的只支持社交联盟登陆
        if (!ProviderLoginType.socialuni.equals(loginQO.getProvider())) {
            throw new SocialParamsException("仅支持社交联盟登录");
        }
        SocialLoginRO<SocialMineUserDetailRO> socialLoginRO = socialProviderLoginDomain.providerLogin(loginQO);

        UserDO mineUser = SocialUserUtil.get(socialLoginRO.getUser().getId());

        //中心授权
        SocialLoginRO<CenterMineUserDetailRO> centerLoginRO = authThirdUserDomain.thirdUserAuthLogin(mineUser, AuthType.user, DevAccountUtils.getDevAccount(), socialLoginRO.getUser());
        return ResultRO.success(centerLoginRO);
    }*/
}
