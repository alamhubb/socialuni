package com.socialuni.social.sdk.factory.user.base;

import com.socialuni.social.entity.model.DO.user.SocialUserPhoneDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.social.model.model.RO.user.SocialUserDetailRO;
import com.socialuni.social.sdk.redis.SocialUserPhoneRedis;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialMineUserDetailROFactory {
    public static SocialUserPhoneRedis socialUserPhoneRedis;

    @Resource
    public void setSocialUserPhoneStore(SocialUserPhoneRedis socialUserPhoneRedis) {
        SocialMineUserDetailROFactory.socialUserPhoneRedis = socialUserPhoneRedis;
    }

    public static SocialMineUserDetailRO getMineUserDetail() {
        UserDO mineUser = SocialUserUtil.getMineUserAllowNull();
        SocialMineUserDetailRO mineUserDetailRO = SocialMineUserDetailROFactory.getMineUserDetail(mineUser);
        return mineUserDetailRO;
    }

    public static SocialMineUserDetailRO getMineUserDetail(UserDO mineUser) {
        //用户关注粉丝数
        SocialUserPhoneDO socialUserPhoneDO = socialUserPhoneRedis.findUserPhoneByUserId(mineUser.getId());

        return SocialMineUserDetailROFactory.getMineUserDetail(mineUser, socialUserPhoneDO);
    }


    public static SocialMineUserDetailRO getMineUserDetail(UserDO mineUser, SocialUserPhoneDO socialUserPhoneDO) {
        //user基础信息
        SocialUserDetailRO socialUserDetailRO = SocialUserDetailROFactory.getUserDetailRO(mineUser, mineUser);

        //user基础信息
        SocialMineUserDetailRO mineUserDetailRO = new SocialMineUserDetailRO(socialUserDetailRO);

        boolean isMine = mineUserDetailRO.getIsMine();

        //user详情信息
        if (isMine) {
            //为自己返回生日，方便修改，和手机号
            mineUserDetailRO.setBirthday(mineUser.getBirthday());
            if (socialUserPhoneDO != null) {
                if (!socialUserPhoneDO.getThirdAuth()){
                    String realPhoneNum = socialUserPhoneDO.getPhoneNum();
                    if (StringUtils.isNotEmpty(realPhoneNum)) {
                        realPhoneNum = realPhoneNum.substring(0, 3) + "*****" + realPhoneNum.substring(8);
                        mineUserDetailRO.setPhoneNum(realPhoneNum);
                    }
                }
            }
        }
        return mineUserDetailRO;
    }
}
