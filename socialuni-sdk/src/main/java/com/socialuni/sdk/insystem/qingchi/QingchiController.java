package com.socialuni.sdk.insystem.qingchi;

import com.socialuni.sdk.feignAPI.insystem.SocialuniQingchiAPI;
import com.socialuni.sdk.model.RO.devAccount.DevAccountRO;
import com.socialuni.sdk.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.sdk.model.QO.SocialBindWxPhoneNumQO;
import com.socialuni.sdk.model.QO.user.OAuthUserInfoQO;
import com.socialuni.sdk.model.QO.user.SocialPhoneNumQO;
import com.socialuni.sdk.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.sdk.model.RO.user.login.SocialLoginRO;
import com.socialuni.sdk.model.RO.user.phone.SocialSendAuthCodeQO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 11:09
 * 前端初始化内容
 */
@RestController
public class QingchiController implements SocialuniQingchiAPI {
    //绑定手机号，绑定微信手机号，发送验证码，手机号登录都是清池专属
    @Resource
    private QingchiService qingchiService;

    @Override
    public ResultRO<SocialLoginRO<CenterMineUserDetailRO>> providerLogin(SocialProviderLoginQO loginQO) {
        return qingchiService.providerLogin(loginQO);
    }

    @Override
    public ResultRO<CenterMineUserDetailRO> bindWxPhoneNum(SocialBindWxPhoneNumQO bindWxPhoneNumQO) {
        return qingchiService.bindWxPhoneNum(bindWxPhoneNumQO);
    }

    @Override
    public ResultRO<Void> sendAuthCode(SocialSendAuthCodeQO authCodeQO) {
        return qingchiService.sendAuthCode(authCodeQO);
    }

    @Override
    public ResultRO<CenterMineUserDetailRO> bindPhoneNum(SocialPhoneNumQO phoneNumQO) {
        return qingchiService.bindPhoneNum(phoneNumQO);
    }

    @Override
    public ResultRO<SocialLoginRO<CenterMineUserDetailRO>> phoneLogin(SocialPhoneNumQO socialPhoneNumQO){
        return qingchiService.phoneLogin(socialPhoneNumQO);
    }

    @Override
    public ResultRO<DevAccountRO> queryDevAccount(OAuthUserInfoQO devAccountQueryQO) {
        return qingchiService.queryDevAccount(devAccountQueryQO);
    }
}
