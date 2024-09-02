package com.socialuni.social.sdk.controller;

import com.socialuni.social.app.logic.service.SocialuniDetailLoginService;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.app.model.SocialuniMineUserDetailRO;
import com.socialuni.social.common.api.model.user.SocialuniUserRO;
import com.socialuni.social.user.sdk.api.user.SocialuniLoginAPI;
import com.socialuni.social.user.sdk.logic.manage.SocialuniTokenManage;
import com.socialuni.social.user.sdk.dao.DO.SocialuniTokenDO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.model.QO.*;
import com.socialuni.social.user.sdk.model.RO.login.SocialLoginRO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 目前认为用不到这个类,社交联盟不提供登录功能，只提供授权功能
 */
@RestController
@RequestMapping("socialuni/login")
public class SocialuniLoginController implements SocialuniLoginAPI {
    @Resource
    private SocialuniDetailLoginService centerLoginService;
    @Resource
    SocialuniTokenManage tokenManage;

    //三方渠道登录，qq、wx、社交联盟，兼容各平台，h5、app、mp
    @Override
    public ResultRO<SocialLoginRO<SocialuniUserRO>> providerLogin(@RequestBody @Valid SocialProviderLoginQO loginData) {
        return centerLoginService.providerLogin(loginData);
    }

    @Override
    public ResultRO<SocialLoginRO<SocialuniUserRO>> phoneLogin(@RequestBody @Valid SocialPhoneNumAuthCodeQO socialPhoneNumQO) {
        ResultRO<SocialLoginRO<SocialuniUserRO>> resultRO = centerLoginService.phoneLogin(socialPhoneNumQO);
        return resultRO;
    }

    @Override
    public ResultRO<SocialLoginRO<SocialuniUserRO>> passwordLogin(SocialPhoneNumPasswordQO socialPhoneNumQO) {
        return centerLoginService.passwordLogin(socialPhoneNumQO);
    }

    @Override
    public ResultRO<SocialLoginRO<SocialuniUserRO>> phonePasswordLogin(SocialPhoneAuthCodePasswordQO socialPhoneNumQO) {
        return centerLoginService.phonePasswordLogin(socialPhoneNumQO);
    }


    @PostMapping("deviceUidLogin")
    ResultRO<SocialLoginRO<SocialuniUserRO>> deviceUidLogin(@RequestBody @Valid SocialuniDeviceUidLoginQO socialuniDeviceUidLoginQO) {
        return centerLoginService.deviceUidLogin(socialuniDeviceUidLoginQO);
    }


    /**
     * 刷新token
     *
     * @return
     */
    @PostMapping("refreshToken")
    public ResultRO<SocialLoginRO<SocialuniMineUserDetailRO>> refreshToken() {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();
        SocialuniTokenDO socialUserTokenDO = tokenManage.create(mineUser.getUnionId());
        return ResultRO.success(new SocialLoginRO(socialUserTokenDO.getToken(), null));
    }

}
