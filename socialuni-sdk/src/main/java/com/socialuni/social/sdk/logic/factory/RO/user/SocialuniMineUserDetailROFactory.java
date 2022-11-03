package com.socialuni.social.sdk.logic.factory.RO.user;

import com.socialuni.social.sdk.dao.redis.SocialUserPhoneRedis;
import com.socialuni.social.sdk.dao.utils.user.SocialuniUserExpandDOUtil;
import com.socialuni.social.sdk.dao.utils.user.SocialuniUserSocialCoinDOUtil;
import com.socialuni.social.sdk.model.RO.user.SocialuniMineUserDetailRO;
import com.socialuni.social.sdk.model.RO.user.SocialuniUserDetailRO;
import com.socialuni.social.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.user.sdk.entity.SocialUserPhoneDo;
import com.socialuni.social.user.sdk.entity.SocialuniUserDo;
import com.socialuni.social.user.sdk.entity.SocialuniUserExpandDo;
import com.socialuni.social.user.sdk.entity.SocialuniUserSocialCoinDo;
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
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserAllowNull();
        SocialuniMineUserDetailRO mineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(mineUser);
        return mineUserDetailRO;
    }

    public static SocialuniMineUserDetailRO getMineUserDetail(Integer mineUserId) {
        SocialuniUserDo mineUser = SocialuniUserUtil.getUserNotNull(mineUserId);
        return SocialuniMineUserDetailROFactory.getMineUserDetail(mineUser);
    }

    public static SocialuniMineUserDetailRO getMineUserDetail(SocialuniUserDo mineUser) {
        //用户关注粉丝数
        SocialUserPhoneDo SocialUserPhoneDo = socialUserPhoneRedis.findUserPhoneByUserId(mineUser.getUnionId());

        return SocialuniMineUserDetailROFactory.getMineUserDetail(mineUser, SocialUserPhoneDo);
    }


    public static SocialuniMineUserDetailRO getMineUserDetail(SocialuniUserDo mineUser, SocialUserPhoneDo SocialUserPhoneDo) {
        //user基础信息
        SocialuniUserDetailRO socialUserDetailRO = SocialuniUserDetailROFactory.getUserDetailRO(mineUser, mineUser);

        //user基础信息
        SocialuniMineUserDetailRO mineUserDetailRO = new SocialuniMineUserDetailRO(socialUserDetailRO);

        Integer mineUnionId = mineUser.getUnionId();

        SocialuniUserExpandDo SocialuniUserExpandDo = SocialuniUserExpandDOUtil.getOrCreate(mineUnionId);

        SocialuniUserSocialCoinDo SocialuniUserSocialCoinDo = SocialuniUserSocialCoinDOUtil.getOrCreate(mineUnionId);

        mineUserDetailRO.setSocialCoin(SocialuniUserSocialCoinDo.getSocialCoin());

        mineUserDetailRO.setOpenContactInfo(SocialuniUserExpandDo.getOpenContactInfo());
//        boolean isMine = mineUserDetailRO.getIsMine();

        //user详情信息
//        if (isMine) {
        //为自己返回生日，方便修改，和手机号
        mineUserDetailRO.setBirthday(mineUser.getBirthday());
        if (SocialUserPhoneDo != null) {
            //只有自己的开发者才显示手机号
            if (DevAccountFacade.getDevIdNotNull().equals(SocialUserPhoneDo.getDevId())) {
                String realPhoneNum = SocialUserPhoneDo.getPhoneNum();
                if (StringUtils.isNotEmpty(realPhoneNum)) {
                    realPhoneNum = realPhoneNum.substring(0, 3) + "*****" + realPhoneNum.substring(8);
                    mineUserDetailRO.setPhoneNum(realPhoneNum);
                }
            }
        }
//        }
        return mineUserDetailRO;
    }
}
