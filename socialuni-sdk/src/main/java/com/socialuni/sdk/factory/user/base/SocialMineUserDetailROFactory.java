package com.socialuni.sdk.factory.user.base;

import com.socialuni.sdk.dao.DO.user.SocialUserPhoneDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.sdk.model.RO.user.SocialUserDetailRO;
import com.socialuni.sdk.dao.redis.SocialUserPhoneRedis;
import com.socialuni.sdk.utils.DevAccountUtils;
import com.socialuni.sdk.utils.SocialuniUserUtil;
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
        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserAllowNull();
        SocialMineUserDetailRO mineUserDetailRO = SocialMineUserDetailROFactory.getMineUserDetail(mineUser);
        return mineUserDetailRO;
    }

    public static SocialMineUserDetailRO getMineUserDetail(SocialuniUserDO mineUser) {
        //用户关注粉丝数
        SocialUserPhoneDO socialUserPhoneDO = socialUserPhoneRedis.findUserPhoneByUserId(mineUser.getUnionId());

        return SocialMineUserDetailROFactory.getMineUserDetail(mineUser, socialUserPhoneDO);
    }


    public static SocialMineUserDetailRO getMineUserDetail(SocialuniUserDO mineUser, SocialUserPhoneDO socialUserPhoneDO) {
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
                if (DevAccountUtils.getDevIdNotNull().equals(socialUserPhoneDO.getDevId())){
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
