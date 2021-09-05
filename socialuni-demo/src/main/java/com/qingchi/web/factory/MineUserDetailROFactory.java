package com.qingchi.web.factory;


import com.qingchi.web.model.DO.UserDetailDO;
import com.qingchi.web.model.RO.user.MineUserDetailRO;
import com.qingchi.web.model.RO.user.UserDetailRO;
import com.qingchi.web.utils.UserUtil;
import com.socialuni.api.feignAPI.SocialuniUserAPI;
import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MineUserDetailROFactory {
    private static SocialuniUserAPI socialuniUserAPI;

    @Resource
    public void setSocialuniUserAPI(SocialuniUserAPI socialuniUserAPI) {
        MineUserDetailROFactory.socialuniUserAPI = socialuniUserAPI;
    }

    public static MineUserDetailRO getMineUser() {
        UserDO mineUser = SocialUserUtil.getMineUserAllowNull();

        //user基础信息
        MineUserDetailRO mineUserDetailRO = MineUserDetailROFactory.getMineUser(mineUser);

        return mineUserDetailRO;
    }

    public static MineUserDetailRO getMineUser(UserDO mineUser) {

        ResultRO<CenterMineUserDetailRO> centerMineUserDetailROResultRO = socialuniUserAPI.getMineUser();

        CenterMineUserDetailRO centerMineUserDetailRO = centerMineUserDetailROResultRO.getData();

        //user基础信息
        MineUserDetailRO mineUserDetailRO = MineUserDetailROFactory.getMineUser(mineUser, centerMineUserDetailRO);

        return mineUserDetailRO;
    }

    public static MineUserDetailRO getMineUser(UserDO mineUser, CenterMineUserDetailRO centerMineUserDetailRO) {

        UserDetailDO userDetailDO = UserUtil.getUserDetail(mineUser.getId());

        //user基础信息
        MineUserDetailRO mineUserDetailRO = MineUserDetailROFactory.providerLoginGetMineUser(mineUser, userDetailDO, centerMineUserDetailRO);

        return mineUserDetailRO;
    }

    public static MineUserDetailRO providerLoginGetMineUser(UserDO mineUser, UserDetailDO userDetailDO, CenterMineUserDetailRO centerMineUserDetailRO) {

        UserDetailRO userDetailRO = UserDetailROFactory.getUserDetailRO(centerMineUserDetailRO, mineUser, userDetailDO);

        //user基础信息
        MineUserDetailRO userDetail = new MineUserDetailRO(userDetailRO, centerMineUserDetailRO);

        //用户关注粉丝数
        //手机号统一由联盟维护就行
        /*
//        SocialUserPhoneDO socialUserPhoneDO = socialUserPhoneStore.findByUserId(mineUser.getId());

        if (socialUserPhoneDO != null) {
            String realPhoneNum = socialUserPhoneDO.getPhoneNum();
            if (StringUtils.isNotEmpty(realPhoneNum)) {
                realPhoneNum = realPhoneNum.substring(0, 3) + "*****" + realPhoneNum.substring(8);
                userDetail.setPhoneNum(realPhoneNum);
            }
        } else {
            userDetail.setPhoneNum(null);
        }*/

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
