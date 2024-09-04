package com.socialuni.social.user.sdk.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniUserRO;
import com.socialuni.social.common.sdk.model.SocialuniImgAddQO;
import com.socialuni.social.tance.sdk.config.SocialuniDevConfig;
import com.socialuni.social.user.sdk.api.user.SocialuniMineUserAPI;
import com.socialuni.social.user.sdk.dao.utils.SocialuniUserExtendFriendLogDOUtil;
import com.socialuni.social.user.sdk.logic.domain.SocialEditUserDomain;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.model.QO.SocialUserEditQO;
import com.socialuni.social.user.sdk.model.factory.SocialuniUserROFactory;
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
