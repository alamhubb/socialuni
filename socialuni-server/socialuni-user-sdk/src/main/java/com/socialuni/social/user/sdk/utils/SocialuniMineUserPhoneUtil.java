package com.socialuni.social.user.sdk.utils;

import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.tance.dev.facade.DevAccountFacade;
import com.qingchi.qing.user.dao.model.SocialUserPhoneDo;
import com.socialuni.social.user.sdk.logic.redis.SocialUserPhoneRedis;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialuniMineUserPhoneUtil {
    public static SocialUserPhoneRedis socialUserPhoneRedis;

    @Resource
    public void setSocialUserPhoneRedis(SocialUserPhoneRedis socialUserPhoneRedis) {
        SocialuniMineUserPhoneUtil.socialUserPhoneRedis = socialUserPhoneRedis;
    }

    public static String getMineUserPhoneNum() {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();

        //用户关注粉丝数
        SocialUserPhoneDo SocialUserPhoneDo = socialUserPhoneRedis.findUserPhoneByUserId(mineUser.getUserId());

        //user详情信息
        if (SocialUserPhoneDo == null) {
            return null;
        }
        //只有自己的开发者才显示手机号
        if (!DevAccountFacade.getDevIdNullElseCenterDevId().equals(SocialUserPhoneDo.getDevId())) {
            return null;
        }
        String realPhoneNum = SocialUserPhoneDo.getPhoneNum();
        if (StringUtils.isEmpty(realPhoneNum)) {
            return null;
        }
        realPhoneNum = realPhoneNum.substring(0, 3) + "*****" + realPhoneNum.substring(8);
        return realPhoneNum;
    }

    public static String getMineUserPhoneNumFull() {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();

        //用户关注粉丝数
        SocialUserPhoneDo SocialUserPhoneDo = socialUserPhoneRedis.findUserPhoneByUserId(mineUser.getUserId());

        //user详情信息
        if (SocialUserPhoneDo == null) {
            return null;
        }
        //只有自己的开发者才显示手机号
        if (!DevAccountFacade.getDevIdNullElseCenterDevId().equals(SocialUserPhoneDo.getDevId())) {
            return null;
        }
        String realPhoneNum = SocialUserPhoneDo.getPhoneNum();
        if (StringUtils.isEmpty(realPhoneNum)) {
            return null;
        }
        return realPhoneNum;
    }

}
