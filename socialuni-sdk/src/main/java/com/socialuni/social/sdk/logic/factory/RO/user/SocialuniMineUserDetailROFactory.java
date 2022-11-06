package com.socialuni.social.sdk.logic.factory.RO.user;

import cn.hutool.extra.spring.SpringUtil;
import com.socialuni.social.im.contrller.SocialuniOpenImUserFeign;
import com.socialuni.social.im.model.SocialuniImUserModel;
import com.socialuni.social.sdk.constant.GenderTypeNumEnum;
import com.socialuni.social.sdk.constant.SocialuniAccountProviderType;
import com.socialuni.social.sdk.dao.redis.SocialUserPhoneRedis;
import com.socialuni.social.sdk.dao.repository.user.SocialUserAccountRepository;
import com.socialuni.social.sdk.dao.utils.user.SocialuniUserExpandDOUtil;
import com.socialuni.social.sdk.dao.utils.user.SocialuniUserSocialCoinDOUtil;
import com.socialuni.social.sdk.logic.domain.user.SocialBindUserOpenImAccountDomain;
import com.socialuni.social.sdk.model.RO.user.SocialuniMineUserDetailRO;
import com.socialuni.social.sdk.model.RO.user.SocialuniUserDetailRO;
import com.socialuni.social.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.sdk.utils.common.BirthdayAgeUtil;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.user.sdk.entity.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class SocialuniMineUserDetailROFactory {
    public static SocialUserPhoneRedis socialUserPhoneRedis;
    public final static SocialUserAccountRepository socialUserAccountRepository = SpringUtil.getBean(SocialUserAccountRepository.class);
    public final static SocialBindUserOpenImAccountDomain socialBindUserOpenImAccountDomain = SpringUtil.getBean(SocialBindUserOpenImAccountDomain.class);
    public final static SocialuniOpenImUserFeign socialuniOpenImUserFeign = SpringUtil.getBean(SocialuniOpenImUserFeign.class);

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

        SocialuniUserExpandDo SocialuniUserExpandDo = SocialuniUserExpandDOUtil.getOrCreate(mineUserId);

        SocialuniUserSocialCoinDo SocialuniUserSocialCoinDo = SocialuniUserSocialCoinDOUtil.getOrCreate(mineUserId);

        mineUserDetailRO.setSocialCoin(SocialuniUserSocialCoinDo.getSocialCoin());

        mineUserDetailRO.setOpenContactInfo(SocialuniUserExpandDo.getOpenContactInfo());

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
        SocialUserAccountDO socialUserAccountDO = socialUserAccountRepository.findByProviderAndUserId(SocialuniAccountProviderType.openIm, mineUserId);
        if (socialUserAccountDO == null) {
            String imToken = socialuniOpenImUserFeign.userLogin(SocialuniMineUserDetailROFactory.toImUserModel(mineUserDetailRO));
            socialUserAccountDO = socialBindUserOpenImAccountDomain.bindOrUpdateUserOpenImAccount(mineUser, socialUserDetailRO.getId(), imToken);
        } else  if (isLogin) {
            //如果为登录，则刷新token
            String token = socialuniOpenImUserFeign.getAndRefreshToken(socialUserDetailRO.getId());
            socialUserAccountDO = socialBindUserOpenImAccountDomain.bindOrUpdateUserOpenImAccount(mineUser, socialUserDetailRO.getId(), token);
        }
        mineUserDetailRO.setImToken(socialUserAccountDO.getSessionKey());

        return mineUserDetailRO;
    }


    public static SocialuniImUserModel toImUserModel(SocialuniMineUserDetailRO user) {
        // 注册到Im
        SocialuniImUserModel imUserModel = new SocialuniImUserModel();
        imUserModel.setUserID(user.getId());
        imUserModel.setNickname(user.getNickname());
        imUserModel.setFaceURL(user.getAvatar());
        imUserModel.setGender(GenderTypeNumEnum.getValueByName(user.getGender()));
        imUserModel.setPhoneNumber(user.getPhoneNum());
        imUserModel.setBirth((int) (BirthdayAgeUtil.getBirthDayByBirthString(user.getBirthday()).getTime() / 1000));
        imUserModel.setCreateTime(new Date());
        return imUserModel;
    }
}
