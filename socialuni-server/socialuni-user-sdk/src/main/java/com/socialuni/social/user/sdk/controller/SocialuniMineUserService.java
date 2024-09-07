package com.socialuni.social.user.sdk.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniUserRO;
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
import com.socialuni.social.user.sdk.model.factory.SocialuniUserROFactory;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

@Service
@Slf4j
public class SocialuniMineUserService {
    @Resource
    SocialEditUserDomain socialEditUserDomain;
    @Resource
    SocialuniMineUserAPI socialuniMineUserAPI;

    public ResultRO<SocialuniUserRO> getMineUser() {
        SocialuniUserRO mineUserDetailRO;
        if (SocialuniDevConfig.hasCenterServer()) {
            ResultRO<SocialuniUserRO> resultRO = socialuniMineUserAPI.getMineUser();
            mineUserDetailRO = new SocialuniUserRO(resultRO.getData());
        } else {
            //生成用户扩列记录
            SocialuniUserExtendFriendLogDOUtil.createUserExtendFriendLog();
            mineUserDetailRO = SocialuniUserROFactory.getMineUserRO();
        }
        return new ResultRO<>(mineUserDetailRO);
    }


    public ResultRO<SocialuniUserRO> editUser(SocialUserEditQO socialUserEditQO) {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();
        socialEditUserDomain.editUser(socialUserEditQO, mineUser);

        SocialuniUserRO socialMineUserDetailRO = SocialuniUserROFactory.getMineUserRO(mineUser);

        if (SocialuniDevConfig.hasCenterServer()) {
            return socialuniMineUserAPI.editUser(socialUserEditQO);
        }

        return ResultRO.success(socialMineUserDetailRO);
    }

    @Resource
    SocialuniTokenManage tokenManage;

    public ResultRO<SocialLoginRO<SocialuniUserRO>> refreshToken() {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();
        Long mineUserId = mineUser.getUserId();
        SocialuniTokenDO socialUserTokenDO = tokenManage.create(mineUser.getUnionId());
        if (SocialuniDevConfig.hasCenterServer()) {
            ResultRO<SocialLoginRO<SocialuniUserRO>> resultRO = socialuniMineUserAPI.refreshToken();
            SocialuniUserRO socialuniUserRO = resultRO.getData().getUser();
            String token = resultRO.getData().getToken();
            Integer serverDevId = DevAccountFacade.getCenterDevIdNotNull();
            SocialuniThirdTokenUtil.updateUserToken(mineUserId, token, serverDevId, socialuniUserRO.getId());
        }

        SocialuniUserDo socialuniUserDo = SocialuniUserUtil.getUserNotNull(mineUserId);

        SocialuniUserRO socialuniMineUserRO = SocialuniUserROFactory.getMineUserRO(socialuniUserDo);

        SocialLoginRO<SocialuniUserRO> socialLoginRO = new SocialLoginRO(socialUserTokenDO.getToken(), socialuniMineUserRO);

        return ResultRO.success(socialLoginRO);
    }



    public ResultRO<SocialuniUserRO> randomUserAvatar() {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();

        SocialuniUserRO socialMineUserDetailRO = socialEditUserDomain.randomUserAvatar(mineUser);

        if (SocialuniDevConfig.hasCenterServer()) {
            return socialuniMineUserAPI.randomUserAvatar();
        }

        return ResultRO.success(socialMineUserDetailRO);
    }

    public ResultRO<SocialuniUserRO> addUserAvatarImg(SocialuniImgAddQO socialUserImgAddQO) {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();


        SocialuniUserRO socialMineUserDetailRO = socialEditUserDomain.addUserAvatarImg(socialUserImgAddQO, mineUser);

        if (SocialuniDevConfig.hasCenterServer()) {
            return socialuniMineUserAPI.addUserAvatarImg(socialUserImgAddQO);
        }

        return ResultRO.success(socialMineUserDetailRO);
    }
}
