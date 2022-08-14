package com.socialuni.sdk.service;

import com.socialuni.sdk.factory.user.base.SocialMineUserDetailROFactory;
import com.socialuni.sdk.factory.user.base.SocialUserDetailROFactory;
import com.socialuni.sdk.domain.user.SocialAddUserImgDomain;
import com.socialuni.sdk.domain.user.SocialDeleteUserImgDomain;
import com.socialuni.sdk.domain.user.SocialEditUserDomain;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.QO.user.SocialUserEditQO;
import com.socialuni.sdk.model.QO.user.SocialUserImgAddQO;
import com.socialuni.sdk.model.QO.user.SocialUserImgDeleteQO;
import com.socialuni.sdk.utils.SocialUserUtil;
import com.socialuni.sdk.model.QO.user.SocialUserIdQO;
import com.socialuni.sdk.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.sdk.model.RO.user.SocialUserDetailRO;
import com.socialuni.social.api.model.ResultRO;
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
        SocialUserDO detailUserDO = SocialUserUtil.getUserNotNull(socialUserIdQO.getUserId());

        SocialUserDO mineUser = SocialUserUtil.getMineUserAllowNull();

        SocialUserDetailRO userDetailRO;

        if (mineUser != null && detailUserDO.getUnionId().equals(mineUser.getUnionId())) {
            userDetailRO = SocialMineUserDetailROFactory.getMineUserDetail(detailUserDO);
        } else {
            userDetailRO = SocialUserDetailROFactory.getUserDetailRO(detailUserDO, mineUser);
        }

        return new ResultRO<>(userDetailRO);
    }

    public ResultRO<SocialMineUserDetailRO> editUser(SocialUserEditQO socialUserEditQO) {
        SocialUserDO mineUser = SocialUserUtil.getMineUserAllowNull();

        SocialMineUserDetailRO socialMineUserDetailRO = socialEditUserDomain.editUser(socialUserEditQO, mineUser);

        return new ResultRO<>(socialMineUserDetailRO);
    }

    public ResultRO<SocialMineUserDetailRO> addUserImg(SocialUserImgAddQO socialUserImgAddQO) {
        SocialUserDO mineUser = SocialUserUtil.getMineUserAllowNull();

        SocialMineUserDetailRO socialMineUserDetailRO = socialAddUserImgDomain.addUserImg(socialUserImgAddQO, mineUser);

        return new ResultRO<>(socialMineUserDetailRO);
    }


    public ResultRO<SocialMineUserDetailRO> deleteUserImg(SocialUserImgDeleteQO socialUserImgDeleteQO) {
        SocialUserDO mineUser = SocialUserUtil.getMineUserAllowNull();

        SocialMineUserDetailRO socialMineUserDetailRO = socialDeleteUserImgDomain.deleteUserImg(socialUserImgDeleteQO, mineUser);

        return new ResultRO<>(socialMineUserDetailRO);
    }


}
