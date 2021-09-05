package com.qingchi.web.service;

import com.qingchi.web.factory.MineUserDetailROFactory;
import com.qingchi.web.factory.UserDetailROFactory;
import com.qingchi.web.model.RO.user.MineUserDetailRO;
import com.qingchi.web.model.RO.user.UserDetailRO;
import com.socialuni.api.feignAPI.SocialuniUserAPI;
import com.socialuni.api.model.QO.user.CenterUserIdQO;
import com.socialuni.api.model.QO.user.CenterUserImgDeleteQO;
import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.api.model.RO.user.CenterUserDetailRO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.QO.user.SocialUserEditQO;
import com.socialuni.social.model.model.QO.user.SocialUserImgAddQO;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class UserService {
    @Resource
    private SocialuniUserAPI socialuniUserAPI;

    public ResultRO<MineUserDetailRO> getMineUser() {
        MineUserDetailRO mineUser = MineUserDetailROFactory.getMineUser();
        return new ResultRO<>(mineUser);
    }

    public ResultRO<UserDetailRO> queryUserDetail(CenterUserIdQO centerUserIdQO) {

        ResultRO<CenterUserDetailRO> centerUserDetailROResultRO = socialuniUserAPI.queryUserDetail(centerUserIdQO);

        CenterUserDetailRO centerUserDetailRO = centerUserDetailROResultRO.getData();

        UserDO mineUser = SocialUserUtil.getMineUserAllowNull();

        UserDetailRO userDetailRO = UserDetailROFactory.getUserDetailRO(centerUserDetailRO, mineUser);
        return new ResultRO<>(userDetailRO);
    }

    public ResultRO<MineUserDetailRO> editUser(SocialUserEditQO socialUserEditQO) {
        ResultRO<CenterMineUserDetailRO> centerMineUserDetailROResultRO = socialuniUserAPI.editUser(socialUserEditQO);
        CenterMineUserDetailRO centerMineUserDetailRO = centerMineUserDetailROResultRO.getData();

        UserDO mineUser = SocialUserUtil.getMineUserAllowNull();

        MineUserDetailRO userDetailRO = MineUserDetailROFactory.getMineUser(mineUser, centerMineUserDetailRO);
        return new ResultRO<>(userDetailRO);
    }

    public ResultRO<MineUserDetailRO> addUserImg(SocialUserImgAddQO socialUserImgAddQO) {
        socialUserImgAddQO.setSrc(socialUserImgAddQO.getSrc());

        ResultRO<CenterMineUserDetailRO> centerMineUserDetailROResultRO = socialuniUserAPI.addUserImg(socialUserImgAddQO);
        CenterMineUserDetailRO centerMineUserDetailRO = centerMineUserDetailROResultRO.getData();

        UserDO mineUser = SocialUserUtil.getMineUserAllowNull();

        MineUserDetailRO userDetailRO = MineUserDetailROFactory.getMineUser(mineUser, centerMineUserDetailRO);
        return new ResultRO<>(userDetailRO);
    }

    public ResultRO<MineUserDetailRO> deleteUserImg(CenterUserImgDeleteQO centerUserImgDeleteQO) {
        ResultRO<CenterMineUserDetailRO> centerMineUserDetailROResultRO = socialuniUserAPI.deleteUserImg(centerUserImgDeleteQO);
        CenterMineUserDetailRO centerMineUserDetailRO = centerMineUserDetailROResultRO.getData();

        UserDO mineUser = SocialUserUtil.getMineUserAllowNull();

        MineUserDetailRO userDetailRO = MineUserDetailROFactory.getMineUser(mineUser, centerMineUserDetailRO);
        return new ResultRO<>(userDetailRO);
    }

}
