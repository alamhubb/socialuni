package com.socialuni.demo.service;


import com.socialuni.api.feignAPI.SocialuniUserAPI;
import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.demo.utils.UserUtil;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.entity.model.DO.user.SocialUserAccountDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.model.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.social.model.model.RO.user.login.SocialLoginRO;
import com.socialuni.social.sdk.service.SocialLoginService;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import com.socialuni.social.sdk.utils.model.SocialUserAccountUtil;
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

    public ResultRO<SocialLoginRO<CenterMineUserDetailRO>> providerLogin(SocialProviderLoginQO loginData) {
        ResultRO<SocialLoginRO<SocialMineUserDetailRO>> resultRO = socialLoginService.providerLogin(loginData);
        SocialLoginRO<SocialMineUserDetailRO> loginRO = resultRO.getData();
        SocialMineUserDetailRO socialMineUserDetailRO = loginRO.getUser();

        UserDO mineUser = SocialUserUtil.get(socialMineUserDetailRO.getId());
        CenterMineUserDetailRO centerMineUserDetailRO = UserUtil.getMineUser(mineUser);

        SocialLoginRO<CenterMineUserDetailRO> socialLoginRO = new SocialLoginRO<>(loginRO.getToken(), centerMineUserDetailRO);
        return ResultRO.success(socialLoginRO);
    }
}
