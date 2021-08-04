package com.socialuni.sdk.factory.user.base;

import com.socialuni.sdk.dao.FollowDao;
import com.socialuni.sdk.model.DO.user.SocialUserPhoneDO;
import com.socialuni.sdk.model.DO.user.UserDO;
import com.socialuni.sdk.store.SocialUserPhoneStore;
import com.socialuni.sdk.utils.SocialUserUtil;
import com.socialuni.social.model.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.social.model.model.RO.user.SocialUserDetailRO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialMineUserDetailROFactory {
    public static SocialUserPhoneStore socialUserPhoneStore;
    static FollowDao followDao;

    @Resource
    public void setFollowDao(FollowDao followDao) {
        SocialMineUserDetailROFactory.followDao = followDao;
    }

    @Resource
    public void setSocialUserPhoneStore(SocialUserPhoneStore socialUserPhoneStore) {
        SocialMineUserDetailROFactory.socialUserPhoneStore = socialUserPhoneStore;
    }

    public static SocialMineUserDetailRO getMineUserDetail() {
        UserDO mineUser = SocialUserUtil.getMineUser();
        SocialMineUserDetailRO mineUserDetailRO = SocialMineUserDetailROFactory.getMineUserDetail(mineUser);
        return mineUserDetailRO;
    }

    public static SocialMineUserDetailRO getMineUserDetail(UserDO mineUser) {
        //user基础信息
        SocialUserDetailRO socialUserDetailRO = SocialUserDetailROFactory.getUserDetailRO(mineUser, mineUser);

        SocialMineUserDetailRO mineUserDetailRO = SocialMineUserDetailROFactory.getMineUserDetail(mineUser, socialUserDetailRO);
        return mineUserDetailRO;
    }

    public static SocialMineUserDetailRO getMineUserDetail(UserDO mineUser, SocialUserDetailRO socialUserDetailRO) {

        //user基础信息
        SocialMineUserDetailRO userDetailVO = new SocialMineUserDetailRO(socialUserDetailRO);

        //用户关注粉丝数
        SocialUserPhoneDO socialUserPhoneDO = socialUserPhoneStore.findByUserId(mineUser.getId());

        boolean isMine = userDetailVO.getIsMine();

        //user详情信息
        if (isMine) {
            //为自己返回生日，方便修改，和手机号
            userDetailVO.setBirthday(mineUser.getBirthday());
            if (socialUserPhoneDO != null) {
                String realPhoneNum = socialUserPhoneDO.getPhoneNum();
                if (StringUtils.isNotEmpty(realPhoneNum)) {
                    realPhoneNum = realPhoneNum.substring(0, 3) + "*****" + realPhoneNum.substring(8);
                    userDetailVO.setPhoneNum(realPhoneNum);
                }
            }
        }

        return userDetailVO;
    }
}
