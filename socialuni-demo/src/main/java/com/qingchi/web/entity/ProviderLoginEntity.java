package com.qingchi.web.entity;

import com.qingchi.web.factory.MineUserDetailROFactory;
import com.qingchi.web.manage.UserDetailManage;
import com.qingchi.web.model.DO.UserDetailDO;
import com.qingchi.web.model.RO.LoginRO;
import com.qingchi.web.model.RO.user.MineUserDetailRO;
import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.social.entity.model.DO.user.SocialUserPhoneDO;
import com.socialuni.social.entity.model.DO.user.TokenDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.model.model.RO.user.login.SocialLoginRO;
import com.socialuni.social.sdk.constant.GenderTypeNumEnum;
import com.socialuni.social.sdk.constant.SocialuniProviderLoginType;
import com.socialuni.social.sdk.constant.platform.PlatformType;
import com.socialuni.social.sdk.entity.user.SocialProviderLoginEntity;
import com.socialuni.social.sdk.manage.TokenManage;
import com.socialuni.social.sdk.manage.phone.SocialUserPhoneManage;
import com.socialuni.social.sdk.redis.SocialUserPhoneRedis;
import com.socialuni.social.utils.IntegerUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class ProviderLoginEntity {
    @Resource
    SocialProviderLoginEntity socialProviderLoginEntity;
    @Resource
    UserDetailManage userDetailManage;
    @Resource
    private TokenManage tokenManage;
    @Resource
    SocialUserPhoneManage socialUserPhoneManage;
    @Resource
    SocialUserPhoneRedis socialUserPhoneRedis;

    public LoginRO centerLogin(SocialLoginRO<CenterMineUserDetailRO> centerMineUserDetailROSocialLoginRO) {
        CenterMineUserDetailRO centerMineUserDetailRO = centerMineUserDetailROSocialLoginRO.getUser();

        SocialProviderLoginQO customLoginQO = new SocialProviderLoginQO();

        customLoginQO.setProvider(SocialuniProviderLoginType.socialuni);
        customLoginQO.setPlatform(PlatformType.mp);
        customLoginQO.setOpenId(centerMineUserDetailRO.getId());
        customLoginQO.setUnionId(centerMineUserDetailRO.getId());
        //使用code作为三方返回的token
        customLoginQO.setCode(centerMineUserDetailROSocialLoginRO.getToken());
        customLoginQO.setNickName(centerMineUserDetailRO.getNickname());
        customLoginQO.setAvatarUrl(centerMineUserDetailRO.getAvatar());
        customLoginQO.setGender(GenderTypeNumEnum.getValueByName(centerMineUserDetailRO.getGender()));
        customLoginQO.setBirthday(centerMineUserDetailRO.getBirthday());
        customLoginQO.setCity(centerMineUserDetailRO.getCity());
//        loginQO.setPhoneNum(centerMineUserDetailRO.getPhoneNum());

        //创建或返回
        UserDO mineUser = socialProviderLoginEntity.providerLogin(customLoginQO);


        String phoneNum = centerMineUserDetailRO.getPhoneNum();


        //这里有坑，中心有可能返回***的，需要判断
        if (StringUtils.isNotEmpty(phoneNum) && IntegerUtils.strIsAllNumber(phoneNum)) {
            //如果用户未绑定手机号，则绑定
            SocialUserPhoneDO socialUserPhoneDO = socialUserPhoneRedis.findUserPhoneByUserId(mineUser.getId());
            // 且手机号未被绑定，才给用户创建手机账户
            SocialUserPhoneDO socialPhoneNumPhoneDO = socialUserPhoneRedis.findByPhoneNum(phoneNum);
            if (socialUserPhoneDO == null && socialPhoneNumPhoneDO == null) {
                socialUserPhoneManage.createUserPhoneNum(mineUser, "86", centerMineUserDetailRO.getPhoneNum());
            }
        }

        UserDetailDO userDetailDO = userDetailManage.getOrCreateUserDetail(mineUser);

        //创建或返回
        //中心授权
//        socialBindUserSocialuniAccountDomain.bindUserSocialAccount(mineUser, centerProviderLoginRO);

        MineUserDetailRO mineUserDetailRO = MineUserDetailROFactory.providerLoginGetMineUser(mineUser, userDetailDO, centerMineUserDetailRO);

        TokenDO tokenDO = tokenManage.create(mineUser.getId());

        return new LoginRO(tokenDO.getToken(), mineUserDetailRO);
    }

}
