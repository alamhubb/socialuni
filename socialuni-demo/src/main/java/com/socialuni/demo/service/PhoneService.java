package com.socialuni.demo.service;


import com.socialuni.api.feignAPI.SocialuniOAuthAPI;
import com.socialuni.demo.model.MineUserDetailRO;
import com.socialuni.demo.utils.UserUtil;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.exception.SocialBusinessException;
import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.model.model.RO.OAuthGetUserPhoneNumRO;
import com.socialuni.social.sdk.entity.user.SocialBindUserProviderAccountEntity;
import com.socialuni.social.sdk.entity.user.SocialUserPhoneEntity;
import com.socialuni.social.sdk.utils.SocialUserUtil;
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
    @Resource
    private SocialBindUserProviderAccountEntity socialBindUserProviderAccountEntity;

    public ResultRO<MineUserDetailRO> bindSocialuniPhoneNum(SocialProviderLoginQO socialBindQO) {
        ResultRO<OAuthGetUserPhoneNumRO> resultRO = socialuniOAuthAPI.oAuthGetUserPhoneNum();

        OAuthGetUserPhoneNumRO OAuthGetUserPhoneNumRO = resultRO.getData();
        if (OAuthGetUserPhoneNumRO == null) {
            throw new SocialBusinessException("用户未授权");
        }

        UserDO mineUser = SocialUserUtil.getMineUser();

        socialBindUserProviderAccountEntity.bindProviderAccount(mineUser.getId(), socialBindQO);

        mineUser = socialUserPhoneEntity.checkPhoneNumAndCreateBind(mineUser, "86", OAuthGetUserPhoneNumRO.getPhoneNum());

        return ResultRO.success(UserUtil.getMineUser(mineUser));
    }
}
