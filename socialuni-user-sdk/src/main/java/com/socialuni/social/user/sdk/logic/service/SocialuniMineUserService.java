package com.socialuni.social.user.sdk.logic.service;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.sdk.model.SocialuniImgAddQO;
import com.socialuni.social.tance.sdk.enumeration.SocialuniSystemConst;
import com.socialuni.social.user.sdk.feignAPI.SocialuniMineUserAPI;
import com.socialuni.social.user.sdk.logic.domain.SocialEditUserDomain;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.model.QO.SocialUserEditQO;
import com.socialuni.social.common.api.model.user.SocialuniUserRO;
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
        if (SocialuniSystemConst.serverIsChild()) {
            ResultRO<SocialuniUserRO> resultRO = socialuniMineUserAPI.getMineUser();
            mineUserDetailRO = new SocialuniUserRO(resultRO.getData());
        } else {
            mineUserDetailRO = SocialuniUserROFactory.getMineUser();
        }
        return new ResultRO<>(mineUserDetailRO);
    }


    public ResultRO<SocialuniUserRO> editUser(SocialUserEditQO socialUserEditQO) {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();
        SocialuniUserRO socialMineUserDetailRO = socialEditUserDomain.editUser(socialUserEditQO, mineUser);

        if (SocialuniSystemConst.serverIsChild()) {
            return socialuniMineUserAPI.editUser(socialUserEditQO);
        }

        return ResultRO.success(socialMineUserDetailRO);
    }


    public ResultRO<SocialuniUserRO> addUserAvatarImg(SocialuniImgAddQO socialUserImgAddQO) {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();


        SocialuniUserRO socialMineUserDetailRO = socialEditUserDomain.addUserAvatarImg(socialUserImgAddQO, mineUser);

        if (SocialuniSystemConst.serverIsChild()) {
            return socialuniMineUserAPI.addUserAvatarImg(socialUserImgAddQO);
        }

        return ResultRO.success(socialMineUserDetailRO);
    }
}
