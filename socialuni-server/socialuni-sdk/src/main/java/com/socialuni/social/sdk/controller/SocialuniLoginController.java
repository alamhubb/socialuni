package com.socialuni.social.sdk.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniMineUserDetailRO;
import com.socialuni.social.sdk.feignAPI.user.SocialuniLoginAPI;
import com.socialuni.social.sdk.logic.service.login.SocialuniDetailLoginService;
import com.socialuni.social.user.sdk.manage.SocialuniTokenManage;
import com.socialuni.social.user.sdk.model.DO.SocialTokenDO;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.model.QO.SocialPhoneNumQO;
import com.socialuni.social.user.sdk.model.QO.SocialProviderLoginQO;
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
    public ResultRO<SocialLoginRO<SocialuniMineUserDetailRO>> providerLogin(@RequestBody @Valid SocialProviderLoginQO loginData) {
        return centerLoginService.providerLogin(loginData);
    }

    @Override
    public ResultRO<SocialLoginRO<SocialuniMineUserDetailRO>> phoneLogin(@RequestBody @Valid SocialPhoneNumQO socialPhoneNumQO) {
        ResultRO<SocialLoginRO<SocialuniMineUserDetailRO>> resultRO = centerLoginService.phoneLogin(socialPhoneNumQO);
        return resultRO;
    }

    /**
     * 刷新token
     * @return
     */
    @PostMapping("refreshToken")
    public ResultRO<SocialLoginRO<SocialuniMineUserDetailRO>> refreshToken(){
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();
        SocialTokenDO socialUserTokenDO = tokenManage.create(mineUser.getUnionId());
        return ResultRO.success(new SocialLoginRO(socialUserTokenDO.getToken(),null));
    }

}
