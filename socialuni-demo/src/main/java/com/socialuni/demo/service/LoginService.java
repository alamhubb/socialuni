package com.socialuni.demo.service;


import com.socialuni.api.feignAPI.SocialuniOAuthAPI;
import com.socialuni.demo.model.MineUserDetailRO;
import com.socialuni.demo.utils.UserUtil;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.model.model.RO.OAuthUserPhoneNumRO;
import com.socialuni.social.model.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.social.model.model.RO.user.login.SocialLoginRO;
import com.socialuni.social.sdk.constant.SocialuniProviderLoginType;
import com.socialuni.social.sdk.entity.user.SocialUserPhoneEntity;
import com.socialuni.social.sdk.service.SocialLoginService;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import com.socialuni.social.web.sdk.utils.SocialTokenUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 11:09
 * 前端初始化内容
 */
@Service
public class LoginService {
    @Resource
    private SocialLoginService socialLoginService;
    @Resource
    private SocialuniOAuthAPI socialuniOAuthAPI;
    @Resource
    private SocialUserPhoneEntity socialUserPhoneEntity;

    public ResultRO<SocialLoginRO<MineUserDetailRO>> providerLogin(SocialProviderLoginQO loginData) {
        ResultRO<SocialLoginRO<SocialMineUserDetailRO>> resultRO = socialLoginService.providerLogin(loginData);
        SocialLoginRO<SocialMineUserDetailRO> loginRO = resultRO.getData();
        SocialMineUserDetailRO socialMineUserDetailRO = loginRO.getUser();
        UserDO mineUser = SocialUserUtil.get(socialMineUserDetailRO.getId());
        //如果为社区联盟登陆则需要模拟设置token
        if (SocialuniProviderLoginType.socialuni.equals(loginData.getProvider())) {
            SocialTokenUtil.setToken(loginRO.getToken());
            //账号绑定成功

            //查询用户是否授权了手机号，授权的话直接绑定手机号
            ResultRO<OAuthUserPhoneNumRO> phoneNumROResultRO = socialuniOAuthAPI.getOAuthUserPhoneNum();

            OAuthUserPhoneNumRO oAuthUserPhoneNumRO = phoneNumROResultRO.getData();

            //授权了手机号，同时绑定手机号
            if (oAuthUserPhoneNumRO != null) {
                mineUser = socialUserPhoneEntity.checkPhoneNumAndCreateBind(mineUser, "86", oAuthUserPhoneNumRO.getPhoneNum());
            }
        }

        MineUserDetailRO mineUserDetailRO = UserUtil.getMineUser(mineUser);

        SocialLoginRO<MineUserDetailRO> socialLoginRO = new SocialLoginRO<>(loginRO.getToken(), mineUserDetailRO);
        return ResultRO.success(socialLoginRO);
    }
}
