package com.socialuni.center.web.factory;/*
package com.socialuni.sdk.factory;


import com.qingchi.web.model.DO.UserDetailDO;
import com.qingchi.web.model.RO.MineUserDetailRO;
import com.qingchi.web.repsotiory.UserDetailRepository;
import com.socialuni.social.model.model.RO.user.SocialUserDetailRO;
import com.socialuni.entity.model.DO.user.UserDO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CenterMineUserDetailROFactory {
    public static UserDetailRepository userDetailRepository;

    @Resource
    public void setUserDetailRepository(UserDetailRepository userDetailRepository) {
        MineUserDetailROFactory.userDetailRepository = userDetailRepository;
    }


    public static MineUserDetailRO getMineUserDetail() {
        UserDO mineUser = CenterUserUtil.getMineUser();
        return MineUserDetailROFactory.getMineUserDetail(mineUser);
    }

    public static MineUserDetailRO getMineUserDetail(UserDO mineUser) {
        //根据用户得到返回详情
        UserDetailDO userDetailDO = userDetailRepository.findOneByUserId(mineUser.getId());

        CenterUserDetailRO centerUserDetailRO = UserDetailROFactory.getUserDetailRO(mineUser, userDetailDO);

        return MineUserDetailROFactory.getMineUserDetail(centerUserDetailRO, userDetailDO);
    }

    public static MineUserDetailRO editUserGetMineUserDetail(UserDO mineUser, UserDetailDO userDetailDO) {
        CenterUserDetailRO centerUserDetailRO = UserDetailROFactory.getUserDetailRO(mineUser, userDetailDO);
        return MineUserDetailROFactory.getMineUserDetail(centerUserDetailRO, userDetailDO);
    }

    //绑定手机号后获取用户详情
    public static MineUserDetailRO bindPhoneNumGetMineUserDetail(SocialUserDetailRO socialUserDetailRO, UserDO mineUser) {
        UserDetailDO userDetailDO = CenterUserUtil.getUserDetail(mineUser.getId());
        CenterUserDetailRO centerUserDetailRO = UserDetailROFactory.getUserDetailRO(socialUserDetailRO, mineUser, userDetailDO);
        return MineUserDetailROFactory.getMineUserDetail(centerUserDetailRO, userDetailDO);
    }

    //登录后获取用户详情
    public static MineUserDetailRO providerLoginGetMineUserDetail(UserDO mineUser, UserDetailLoginEO userDetailLoginEO) {
        //用户关注粉丝数
        CenterUserDetailRO centerUserDetailRO = UserDetailROFactory.providerLoginGetMineUserDetail(mineUser, userDetailLoginEO.getUserDetailDO(), userDetailLoginEO.getUserFollowDetailDO());

        return MineUserDetailROFactory.getMineUserDetail(centerUserDetailRO, userDetailLoginEO.getUserDetailDO());
    }

    public static MineUserDetailRO getMineUserDetail(CenterUserDetailRO centerUserDetailRO, UserDetailDO userDetailDO) {
        //user基础信息
        MineUserDetailRO userDetail = new MineUserDetailRO(centerUserDetailRO);

        userDetail.setVipEndDate(userDetailDO.getVipEndDate());
        userDetail.setIdCardStatus(userDetailDO.getIdCardStatus());
        userDetail.setShell(userDetailDO.getShell());
        userDetail.setGenderModified(userDetailDO.getGenderModified());
        userDetail.setAuthNum(userDetailDO.getAuthNum());
        userDetail.setGradeLevel(userDetailDO.getGradeLevel());
        userDetail.setWealthLevel(userDetailDO.getWealthLevel());

        return userDetail;
    }
}
*/
