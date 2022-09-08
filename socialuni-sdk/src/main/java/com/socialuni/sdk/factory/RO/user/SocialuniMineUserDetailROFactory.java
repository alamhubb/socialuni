package com.socialuni.sdk.factory.RO.user;

import com.socialuni.sdk.dao.DO.user.SocialUserPhoneDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.model.RO.user.SocialuniMineUserDetailRO;
import com.socialuni.sdk.dao.redis.SocialUserPhoneRedis;
import com.socialuni.sdk.model.RO.user.SocialuniUserDetailRO;
import com.socialuni.sdk.utils.DevAccountUtils;
import com.socialuni.sdk.utils.SocialuniUserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialuniMineUserDetailROFactory {
    public static SocialUserPhoneRedis socialUserPhoneRedis;

    @Resource
    public void setSocialUserPhoneStore(SocialUserPhoneRedis socialUserPhoneRedis) {
        SocialuniMineUserDetailROFactory.socialUserPhoneRedis = socialUserPhoneRedis;
    }

    public static SocialuniMineUserDetailRO getMineUserDetail() {
        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserAllowNull();
        SocialuniMineUserDetailRO mineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(mineUser);
        return mineUserDetailRO;
    }

    public static SocialuniMineUserDetailRO getMineUserDetail(SocialuniUserDO mineUser) {
        //用户关注粉丝数
        SocialUserPhoneDO socialUserPhoneDO = socialUserPhoneRedis.findUserPhoneByUserId(mineUser.getUnionId());

        return SocialuniMineUserDetailROFactory.getMineUserDetail(mineUser, socialUserPhoneDO);
    }


    public static SocialuniMineUserDetailRO getMineUserDetail(SocialuniUserDO mineUser, SocialUserPhoneDO socialUserPhoneDO) {
        //user基础信息
        SocialuniUserDetailRO socialUserDetailRO = SocialuniUserDetailROFactory.getUserDetailRO(mineUser, mineUser);

        //user基础信息
        SocialuniMineUserDetailRO mineUserDetailRO = new SocialuniMineUserDetailRO(socialUserDetailRO);

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
