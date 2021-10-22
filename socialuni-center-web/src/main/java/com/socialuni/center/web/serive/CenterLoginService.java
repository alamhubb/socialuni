package com.socialuni.center.web.serive;

import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.center.sdk.mode.DevAccountDO;
import com.socialuni.center.sdk.utils.DevAccountUtils;
import com.socialuni.center.web.domain.thirdUser.AuthThirdUserDomain;
import com.socialuni.center.web.factory.RO.user.CenterMineUserDetailROFactory;
import com.socialuni.center.web.utils.CenterUserUtil;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.entity.model.DO.user.SocialUserPhoneDO;
import com.socialuni.social.entity.model.DO.user.TokenDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.exception.SocialParamsException;
import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.model.model.RO.OAuthGetUserPhoneNumRO;
import com.socialuni.social.model.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.social.model.model.RO.user.login.SocialLoginRO;
import com.socialuni.social.sdk.constant.AuthType;
import com.socialuni.social.sdk.constant.platform.UniappProviderType;
import com.socialuni.social.sdk.domain.login.SocialProviderLoginDomain;
import com.socialuni.social.sdk.entity.user.SocialProviderLoginEntity;
import com.socialuni.social.sdk.factory.user.base.SocialMineUserDetailROFactory;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import com.socialuni.social.web.sdk.utils.SocialTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
    @Resource
    private SocialProviderLoginEntity socialProviderLoginEntity;

    //提供给借用社交联盟实现微信qq渠道登录的开发者， 不需要支持社交联盟登录，社交联盟登录是前台跳转登录返回信息，不走后台
    /*@Transactional
    public ResultRO<SocialLoginRO<CenterMineUserDetailRO>> providerLogin(SocialProviderLoginQO loginQO) {
        // 只有清池支持渠道登录
        // 其他的只支持社交联盟登陆
        if (!UniappProviderType.values.contains(loginQO.getProvider())) {
            throw new SocialParamsException(UniappProviderType.notSupportTypeErrorMsg);
        }

        DevAccountDO devAccountDO = DevAccountUtils.getDevAccountNotNull();
        loginQO.setDevId(devAccountDO.getId());
        SocialLoginRO<SocialMineUserDetailRO> socialLoginRO = socialProviderLoginDomain.providerLogin(loginQO);

        UserDO mineUser = SocialUserUtil.get(socialLoginRO.getUser().getId());

        //中心授权
        SocialLoginRO<CenterMineUserDetailRO> centerLoginRO = authThirdUserDomain.thirdUserAuthLogin(mineUser, AuthType.user, devAccountDO, socialLoginRO.getUser());
        return ResultRO.success(centerLoginRO);
    }*/

    public ResultRO<SocialLoginRO<CenterMineUserDetailRO>> socialuniPhoneLogin(SocialProviderLoginQO loginData) {
        UserDO mineUser = CenterUserUtil.getMineUser(loginData.getCode());

        CenterMineUserDetailRO centerMineUserDetailRO = CenterMineUserDetailROFactory.getMineUserDetail(mineUser);

        SocialLoginRO<CenterMineUserDetailRO> socialLoginRO = new SocialLoginRO<>(loginData.getCode(), centerMineUserDetailRO);
        return ResultRO.success(socialLoginRO);
    }
}
