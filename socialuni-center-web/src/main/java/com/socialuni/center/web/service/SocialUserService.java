package com.socialuni.center.web.service;

import com.socialuni.center.web.factory.user.base.SocialMineUserDetailROFactory;
import com.socialuni.center.web.factory.user.base.SocialUserDetailROFactory;
import com.socialuni.center.web.domain.user.SocialAddUserImgDomain;
import com.socialuni.center.web.domain.user.SocialDeleteUserImgDomain;
import com.socialuni.center.web.domain.user.SocialEditUserDomain;
import com.socialuni.center.web.model.DO.user.UserDO;
import com.socialuni.center.web.model.QO.user.SocialUserEditQO;
import com.socialuni.center.web.model.QO.user.SocialUserImgAddQO;
import com.socialuni.center.web.model.QO.user.SocialUserImgDeleteQO;
import com.socialuni.center.web.utils.SocialUserUtil;
import com.socialuni.center.web.model.QO.user.SocialUserIdQO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.center.web.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.center.web.model.RO.user.SocialUserDetailRO;
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
        UserDO detailUserDO = SocialUserUtil.getNotNull(socialUserIdQO.getUserId());

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
