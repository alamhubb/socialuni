package com.socialuni.sdk.service;

import com.socialuni.sdk.factory.user.base.SocialMineUserDetailROFactory;
import com.socialuni.sdk.factory.user.base.SocialUserDetailROFactory;
import com.socialuni.sdk.model.DO.user.UserDO;
import com.socialuni.sdk.utils.SocialUserUtil;
import com.socialuni.social.model.model.QO.user.SocialUserIdQO;
import com.socialuni.social.model.model.RO.ResultRO;
import com.socialuni.social.model.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.social.model.model.RO.user.SocialUserDetailRO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SocialUserService {

    public ResultRO<SocialMineUserDetailRO> getMineUser() {
        SocialMineUserDetailRO mineUser = SocialMineUserDetailROFactory.getMineUserDetail();
        return new ResultRO<>(mineUser);
    }

    public ResultRO<SocialUserDetailRO> queryUserDetail(SocialUserIdQO socialUserIdQO) {
        UserDO detailUserDO = SocialUserUtil.get(socialUserIdQO.getUserId());

        UserDO mineUser = SocialUserUtil.getMineUser();

        SocialUserDetailRO userDetailRO;

        if (mineUser != null && detailUserDO.getId().equals(mineUser.getId())) {
            userDetailRO = SocialMineUserDetailROFactory.getMineUserDetail(detailUserDO);
        } else {
            userDetailRO = SocialUserDetailROFactory.getUserDetailRO(detailUserDO, mineUser);
        }

        return new ResultRO<>(userDetailRO);
    }

}
