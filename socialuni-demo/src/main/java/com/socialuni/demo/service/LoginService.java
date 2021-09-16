package com.socialuni.demo.service;


import com.socialuni.api.feignAPI.SocialuniUserAPI;
import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.demo.model.MineUserDetailRO;
import com.socialuni.demo.utils.UserUtil;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.entity.model.DO.user.SocialUserAccountDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.model.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.social.model.model.RO.user.login.SocialLoginRO;
import com.socialuni.social.sdk.constant.SocialuniProviderLoginType;
import com.socialuni.social.sdk.service.SocialLoginService;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import com.socialuni.social.sdk.utils.model.SocialUserAccountUtil;
import com.socialuni.social.web.sdk.utils.RequestUtil;
import com.socialuni.social.web.sdk.utils.SocialTokenUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 11:09
 * 前端初始化内容
 */
@Service
public class LoginService {
    @Resource
    private SocialLoginService socialLoginService;

    public ResultRO<SocialLoginRO<MineUserDetailRO>> providerLogin(SocialProviderLoginQO loginData) {
        ResultRO<SocialLoginRO<SocialMineUserDetailRO>> resultRO = socialLoginService.providerLogin(loginData);
        SocialLoginRO<SocialMineUserDetailRO> loginRO = resultRO.getData();
        SocialMineUserDetailRO socialMineUserDetailRO = loginRO.getUser();

        UserDO mineUser = SocialUserUtil.get(socialMineUserDetailRO.getId());

        //如果为社区联盟登陆则需要模拟设置token
        if (SocialuniProviderLoginType.socialuni.equals(loginData.getProvider())) {
            SocialTokenUtil.setToken(loginRO.getToken());
        }

        MineUserDetailRO mineUserDetailRO = UserUtil.getMineUser(mineUser);

        SocialLoginRO<MineUserDetailRO> socialLoginRO = new SocialLoginRO<>(loginRO.getToken(), mineUserDetailRO);
        return ResultRO.success(socialLoginRO);
    }
}
