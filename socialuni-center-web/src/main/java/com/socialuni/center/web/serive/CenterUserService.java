package com.socialuni.center.web.serive;

import com.socialuni.api.model.QO.user.CenterUserIdQO;
import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.api.model.RO.user.CenterUserDetailRO;
import com.socialuni.center.web.factory.RO.user.CenterMineUserDetailROFactory;
import com.socialuni.center.web.factory.RO.user.CenterUserDetailROFactory;
import com.socialuni.center.web.utils.CenterUserUtil;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.social.sdk.domain.user.SocialEditUserDomain;
import com.socialuni.social.model.model.QO.user.SocialUserEditQO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class CenterUserService {
    @Resource
    SocialEditUserDomain socialEditUserDomain;

    public ResultRO<CenterMineUserDetailRO> getMineUser() {
        CenterMineUserDetailRO mineUser = CenterMineUserDetailROFactory.getMineUserDetail();
        return new ResultRO<>(mineUser);
    }

    public ResultRO<CenterUserDetailRO> queryUserDetail(CenterUserIdQO centerUserIdQO) {
        UserDO detailUserDO = CenterUserUtil.get(centerUserIdQO.getUserId());

        UserDO mineUser = CenterUserUtil.getMineUser();

        CenterUserDetailRO userDetailRO;

        if (mineUser != null && detailUserDO.getId().equals(mineUser.getId())) {
            userDetailRO = CenterMineUserDetailROFactory.getMineUserDetail(detailUserDO);
        } else {
            userDetailRO = CenterUserDetailROFactory.getUserDetailRO(detailUserDO, mineUser);
        }

        return new ResultRO<>(userDetailRO);
    }


    public ResultRO<CenterMineUserDetailRO> editUser(SocialUserEditQO socialUserEditQO) {
        UserDO mineUser = CenterUserUtil.getMineUser();
        SocialMineUserDetailRO socialMineUserDetailRO = socialEditUserDomain.editUser(socialUserEditQO, mineUser);

        CenterMineUserDetailRO centerMineUserDetailRO = CenterMineUserDetailROFactory.getMineUserDetail(socialMineUserDetailRO, mineUser);

        return ResultRO.success(centerMineUserDetailRO);
    }
}
