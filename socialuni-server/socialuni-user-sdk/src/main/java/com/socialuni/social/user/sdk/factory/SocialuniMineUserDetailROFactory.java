package com.socialuni.social.user.sdk.factory;

import cn.hutool.extra.spring.SpringUtil;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.user.sdk.dao.DO.SocialUserPhoneDo;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.dao.DO.SocialuniUserExpandDo;
import com.socialuni.social.user.sdk.dao.DO.SocialuniUserCoinDo;
import com.socialuni.social.common.api.model.user.SocialuniMineUserDetailRO;
import com.socialuni.social.common.api.model.user.SocialuniUserDetailRO;
import com.socialuni.social.user.sdk.dao.utils.SocialuniUserExpandDOUtil;
import com.socialuni.social.user.sdk.logic.domain.SocialBindUserOpenImAccountDomain;
import com.socialuni.social.user.sdk.logic.redis.SocialUserPhoneRedis;
import com.socialuni.social.common.sdk.dao.repository.SocialUserPlatformAccountRepository;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialuniMineUserDetailROFactory {
    public static SocialUserPhoneRedis socialUserPhoneRedis;
    public final static SocialUserPlatformAccountRepository SOCIAL_USER_PLATFORM_ACCOUNT_REPOSITORY = SpringUtil.getBean(SocialUserPlatformAccountRepository.class);
    public final static SocialBindUserOpenImAccountDomain socialBindUserOpenImAccountDomain = SpringUtil.getBean(SocialBindUserOpenImAccountDomain.class);
//    public final static SocialuniOpenImUserFeign socialuniOpenImUserFeign = SpringUtil.getBean(SocialuniOpenImUserFeign.class);

    @Resource
    public void setSocialUserPhoneStore(SocialUserPhoneRedis socialUserPhoneRedis) {
        SocialuniMineUserDetailROFactory.socialUserPhoneRedis = socialUserPhoneRedis;
    }

    public static SocialuniMineUserDetailRO getMineUserDetail() {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();
        SocialuniMineUserDetailRO mineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(mineUser);
        return mineUserDetailRO;
    }

    public static SocialuniMineUserDetailRO getMineUserDetail(Integer mineUserId) {
        SocialuniUserDo mineUser = SocialuniUserUtil.getAndCheckUserNotNull(mineUserId);
        return SocialuniMineUserDetailROFactory.getMineUserDetail(mineUser);
    }

    public static SocialuniMineUserDetailRO getMineUserDetail(SocialuniUserDo mineUser) {
        return SocialuniMineUserDetailROFactory.getMineUserDetail(mineUser, false);
    }

    public static SocialuniMineUserDetailRO getMineUserDetail(SocialuniUserDo mineUser, Boolean isLogin) {
        //用户关注粉丝数
        SocialUserPhoneDo SocialUserPhoneDo = socialUserPhoneRedis.findUserPhoneByUserId(mineUser.getUnionId());

        return SocialuniMineUserDetailROFactory.getMineUserDetail(mineUser, SocialUserPhoneDo, isLogin);
    }


    public static SocialuniMineUserDetailRO getMineUserDetail(SocialuniUserDo mineUser, SocialUserPhoneDo SocialUserPhoneDo, Boolean isLogin) {
        //user基础信息
        SocialuniUserDetailRO socialUserDetailRO = SocialuniUserDetailROFactory.getUserDetailRO(mineUser, mineUser);

        //user基础信息
        SocialuniMineUserDetailRO mineUserDetailRO = new SocialuniMineUserDetailRO(socialUserDetailRO);

        Integer mineUserId = mineUser.getUnionId();

        SocialuniUserExpandDo socialuniUserExpandDo = SocialuniUserExpandDOUtil.getOrCreate(mineUserId);

        SocialuniUserCoinDo SocialuniUserSocialCoinDo = SocialuniUserSocialCoinDOUtil.getOrCreate(mineUserId);

        mineUserDetailRO.setSocialCoin(SocialuniUserSocialCoinDo.getCoin());

        mineUserDetailRO.setOpenContactInfo(socialuniUserExpandDo.getOpenContactInfo());

        //为自己返回生日，方便修改，和手机号
        mineUserDetailRO.setBirthday(mineUser.getBirthday());

        //user详情信息
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

        //设置openIm的key
        /*SocialUserAccountDO socialUserAccountDO = socialUserAccountRepository.findByProviderAndUserId(SocialuniAccountProviderType.openIm, mineUserId);
        if (socialUserAccountDO == null) {
            String imToken = socialuniOpenImUserFeign.userLogin(SocialuniMineUserDetailROFactory.toImUserModel(mineUserDetailRO));
            socialUserAccountDO = socialBindUserOpenImAccountDomain.bindOrUpdateUserOpenImAccount(mineUser, socialUserDetailRO.getId(), imToken);
        } else  if (isLogin) {
            //如果为登录，则刷新token
            String token = socialuniOpenImUserFeign.getAndRefreshToken(socialUserDetailRO.getId());
            socialUserAccountDO = socialBindUserOpenImAccountDomain.bindOrUpdateUserOpenImAccount(mineUser, socialUserDetailRO.getId(), token);
        }
        mineUserDetailRO.setImToken(socialUserAccountDO.getSessionKey());*/

        return mineUserDetailRO;
    }


}
