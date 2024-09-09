package com.socialuni.social.user.sdk.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniUserShowRO;
import com.socialuni.social.common.sdk.model.SocialuniImgAddQO;
import com.socialuni.social.tance.dev.config.SocialuniDevConfig;
import com.socialuni.social.tance.dev.facade.DevAccountFacade;
import com.socialuni.social.tance.dev.util.SocialuniThirdTokenUtil;
import com.socialuni.social.user.sdk.api.user.SocialuniMineUserAPI;
import com.socialuni.social.user.sdk.dao.DO.SocialuniTokenDO;
import com.socialuni.social.user.sdk.dao.utils.SocialuniUserExtendFriendLogDOUtil;
import com.socialuni.social.user.sdk.logic.domain.SocialEditUserDomain;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.logic.manage.SocialuniTokenManage;
import com.socialuni.social.user.sdk.model.QO.SocialUserEditQO;
import com.socialuni.social.user.sdk.model.RO.login.SocialLoginRO;
import com.socialuni.social.user.sdk.model.factory.SocialuniUserShowROFactory;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class SocialuniMineUserService {
    @Resource
    SocialEditUserDomain socialEditUserDomain;
    @Resource
    SocialuniMineUserAPI socialuniMineUserAPI;

    public ResultRO<SocialuniUserShowRO> getMineUser() {
        SocialuniUserShowRO mineUserDetailRO;
        if (SocialuniDevConfig.hasCenterServer()) {
            ResultRO<SocialuniUserShowRO> resultRO = socialuniMineUserAPI.getMineUser();
            mineUserDetailRO = new SocialuniUserShowRO(resultRO.getData());
        } else {
            //生成用户扩列记录
            SocialuniUserExtendFriendLogDOUtil.createUserExtendFriendLog();
            mineUserDetailRO = SocialuniUserShowROFactory.getMineUserRO();
        }
        return new ResultRO<>(mineUserDetailRO);
    }


    public ResultRO<SocialuniUserShowRO> editUser(SocialUserEditQO socialUserEditQO) {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();
        socialEditUserDomain.editUser(socialUserEditQO, mineUser);

        SocialuniUserShowRO socialMineUserDetailRO = SocialuniUserShowROFactory.getMineUserRO(mineUser);

        if (SocialuniDevConfig.hasCenterServer()) {
            return socialuniMineUserAPI.editUser(socialUserEditQO);
        }

        return ResultRO.success(socialMineUserDetailRO);
    }

    @Resource
    SocialuniTokenManage tokenManage;

    public ResultRO<SocialLoginRO<SocialuniUserShowRO>> refreshToken() {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();
        Long mineUserId = mineUser.getUserId();
        SocialuniTokenDO socialUserTokenDO = tokenManage.create(mineUser.getUnionId());
        if (SocialuniDevConfig.hasCenterServer()) {
            ResultRO<SocialLoginRO<SocialuniUserShowRO>> resultRO = socialuniMineUserAPI.refreshToken();
            SocialuniUserShowRO socialuniUserRO = resultRO.getData().getUser();
            String token = resultRO.getData().getToken();
            Integer serverDevId = DevAccountFacade.getCenterDevIdNotNull();
            SocialuniThirdTokenUtil.updateUserToken(mineUserId, token, serverDevId, socialuniUserRO.getId());
        }

        SocialuniUserDo socialuniUserDo = SocialuniUserUtil.getUserNotNull(mineUserId);

        SocialuniUserShowRO socialuniMineUserRO = SocialuniUserShowROFactory.getMineUserRO(socialuniUserDo);

        SocialLoginRO<SocialuniUserShowRO> socialLoginRO = new SocialLoginRO(socialUserTokenDO.getToken(), socialuniMineUserRO);

        return ResultRO.success(socialLoginRO);
    }



    public ResultRO<SocialuniUserShowRO> randomUserAvatar() {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();

        SocialuniUserShowRO socialMineUserDetailRO = socialEditUserDomain.randomUserAvatar(mineUser);

        if (SocialuniDevConfig.hasCenterServer()) {
            return socialuniMineUserAPI.randomUserAvatar();
        }

        return ResultRO.success(socialMineUserDetailRO);
    }

    public ResultRO<SocialuniUserShowRO> addUserAvatarImg(SocialuniImgAddQO socialUserImgAddQO) {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();


        SocialuniUserShowRO socialMineUserDetailRO = socialEditUserDomain.addUserAvatarImg(socialUserImgAddQO, mineUser);

        if (SocialuniDevConfig.hasCenterServer()) {
            return socialuniMineUserAPI.addUserAvatarImg(socialUserImgAddQO);
        }

        return ResultRO.success(socialMineUserDetailRO);
    }
}
