package com.qingchi.web.service;


import com.qingchi.web.factory.MineUserDetailROFactory;
import com.qingchi.web.model.RO.user.MineUserDetailRO;
import com.socialuni.api.feignAPI.SocialuniMockOAuthAPI;
import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.RO.user.login.SocialLoginRO;
import com.socialuni.social.sdk.entity.user.SocialUserPhoneEntity;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MockOAuthAuthService {
    @Resource
    SocialuniMockOAuthAPI socialuniMockOAuthAPI;
    @Resource
    SocialUserPhoneEntity socialUserPhoneEntity;

    public ResultRO<MineUserDetailRO> mockOAuthUserPhoneNum() {
        UserDO mineUser = SocialUserUtil.getMineUserAllowNull();

        ResultRO<SocialLoginRO<CenterMineUserDetailRO>> resultRO = socialuniMockOAuthAPI.mockOAuthUserPhoneNum();

        CenterMineUserDetailRO centerMineUserDetailRO = resultRO.getData().getUser();

        mineUser = socialUserPhoneEntity.checkPhoneNumAndCreateBind(mineUser, "86", centerMineUserDetailRO.getPhoneNum());

        MineUserDetailRO mineUserDetailRO = MineUserDetailROFactory.getMineUser(mineUser, centerMineUserDetailRO);
        mineUserDetailRO.setPhoneNum(centerMineUserDetailRO.getPhoneNum());
        return new ResultRO<>(mineUserDetailRO);
    }
}