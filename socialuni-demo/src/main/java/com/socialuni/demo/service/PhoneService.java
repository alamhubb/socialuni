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
public class PhoneService {
    @Resource
    private SocialuniOAuthAPI socialuniOAuthAPI;
    @Resource
    private SocialUserPhoneEntity socialUserPhoneEntity;

    public ResultRO<MineUserDetailRO> bindSocialuniPhoneNum() {
        ResultRO<OAuthUserPhoneNumRO> resultRO = socialuniOAuthAPI.getOAuthUserPhoneNum();

        UserDO mineUser = SocialUserUtil.getMineUserNotNull();

        mineUser = socialUserPhoneEntity.checkPhoneNumAndCreateBind(mineUser, "86", resultRO.getData().getPhoneNum());

        return ResultRO.success(UserUtil.getMineUser(mineUser));
    }
}
