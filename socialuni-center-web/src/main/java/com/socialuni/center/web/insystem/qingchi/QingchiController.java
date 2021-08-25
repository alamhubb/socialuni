package com.socialuni.center.web.insystem.qingchi;

import com.socialuni.api.feignAPI.insystem.SocialuniQingchiAPI;
import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.social.model.model.QO.SocialBindWxPhoneNumQO;
import com.socialuni.social.model.model.QO.user.SocialPhoneNumQO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.model.model.RO.user.login.SocialLoginRO;
import com.socialuni.social.model.model.RO.user.phone.SocialSendAuthCodeQO;
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

    public ResultRO<CenterMineUserDetailRO> bindWxPhoneNum(SocialBindWxPhoneNumQO bindWxPhoneNumQO) {
        return qingchiService.bindWxPhoneNum(bindWxPhoneNumQO);
    }

    public ResultRO<Void> sendAuthCode(SocialSendAuthCodeQO authCodeQO) {
        return qingchiService.sendAuthCode(authCodeQO);
    }

    public ResultRO<CenterMineUserDetailRO> bindPhoneNum(SocialPhoneNumQO phoneNumQO) {
        return qingchiService.bindPhoneNum(phoneNumQO);
    }

    public ResultRO<SocialLoginRO<CenterMineUserDetailRO>> phoneLogin(SocialPhoneNumQO socialPhoneNumQO){
        return qingchiService.phoneLogin(socialPhoneNumQO);
    }
}
