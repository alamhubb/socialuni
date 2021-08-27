package com.socialuni.social.sdk.service;

import com.socialuni.social.sdk.domain.user.SocialAddUserImgDomain;
import com.socialuni.social.sdk.domain.user.SocialDeleteUserImgDomain;
import com.socialuni.social.sdk.domain.user.SocialEditUserDomain;
import com.socialuni.social.sdk.factory.user.base.SocialMineUserDetailROFactory;
import com.socialuni.social.sdk.factory.user.base.SocialUserDetailROFactory;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.QO.user.SocialUserEditQO;
import com.socialuni.social.model.model.QO.user.SocialUserImgAddQO;
import com.socialuni.social.model.model.QO.user.SocialUserImgDeleteQO;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import com.socialuni.social.model.model.QO.user.SocialUserIdQO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.social.model.model.RO.user.SocialUserDetailRO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class SocialUserService {
    @Resource
    SocialEditUserDomain socialEditUserDomain;
    @Resource
    SocialAddUserImgDomain socialAddUserImgDomain;
    @Resource
    SocialDeleteUserImgDomain socialDeleteUserImgDomain;


    public ResultRO<SocialMineUserDetailRO> getMineUser() {
        SocialMineUserDetailRO mineUser = SocialMineUserDetailROFactory.getMineUserDetail();
        return new ResultRO<>(mineUser);
    }

    public ResultRO<SocialUserDetailRO> queryUserDetail(SocialUserIdQO socialUserIdQO) {
        UserDO detailUserDO = SocialUserUtil.get(socialUserIdQO.getUserId());

        UserDO mineUser = SocialUserUtil.getMineUserAllowNull();

        SocialUserDetailRO userDetailRO;

        if (mineUser != null && detailUserDO.getId().equals(mineUser.getId())) {
            userDetailRO = SocialMineUserDetailROFactory.getMineUserDetail(detailUserDO);
        } else {
            userDetailRO = SocialUserDetailROFactory.getUserDetailRO(detailUserDO, mineUser);
        }

        return new ResultRO<>(userDetailRO);
    }

    public ResultRO<SocialMineUserDetailRO> editUser(SocialUserEditQO socialUserEditQO) {
        UserDO mineUser = SocialUserUtil.getMineUserAllowNull();

        SocialMineUserDetailRO socialMineUserDetailRO = socialEditUserDomain.editUser(socialUserEditQO, mineUser);

        return new ResultRO<>(socialMineUserDetailRO);
    }

    public ResultRO<SocialMineUserDetailRO> addUserImg(SocialUserImgAddQO socialUserImgAddQO) {
        UserDO mineUser = SocialUserUtil.getMineUserAllowNull();

        SocialMineUserDetailRO socialMineUserDetailRO = socialAddUserImgDomain.addUserImg(socialUserImgAddQO, mineUser);

        return new ResultRO<>(socialMineUserDetailRO);
    }


    public ResultRO<SocialMineUserDetailRO> deleteUserImg(SocialUserImgDeleteQO socialUserImgDeleteQO) {
        UserDO mineUser = SocialUserUtil.getMineUserAllowNull();

        SocialMineUserDetailRO socialMineUserDetailRO = socialDeleteUserImgDomain.deleteUserImg(socialUserImgDeleteQO, mineUser);

        return new ResultRO<>(socialMineUserDetailRO);
    }


}
