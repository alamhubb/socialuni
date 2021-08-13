package com.socialuni.social.sdk.domain.login;

import com.socialuni.social.sdk.factory.user.base.SocialMineUserDetailROFactory;
import com.socialuni.social.sdk.manage.SocialUserFansDetailManage;
import com.socialuni.social.sdk.manage.SocialUserManage;
import com.socialuni.social.sdk.manage.TokenManage;
import com.socialuni.social.sdk.manage.phone.AuthenticationManage;
import com.socialuni.social.sdk.manage.phone.SocialUserPhoneManage;
import com.socialuni.entity.model.DO.user.SocialUserPhoneDO;
import com.socialuni.entity.model.DO.user.TokenDO;
import com.socialuni.entity.model.DO.user.UserDO;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import com.socialuni.social.model.model.QO.user.SocialPhoneNumQO;
import com.socialuni.social.model.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.social.model.model.RO.user.login.SocialLoginRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
public class SocialPhoneLoginDomain {
    @Resource
    SocialUserManage socialUserManage;
    @Resource
    SocialUserPhoneManage socialUserPhoneManage;
    @Resource
    AuthenticationManage authenticationManage;
    @Resource
    TokenManage tokenManage;
    @Resource
    SocialUserFansDetailManage socialUserFansDetailManage;

    @Transactional
    public SocialLoginRO<SocialMineUserDetailRO> phoneLogin(SocialPhoneNumQO socialPhoneNumQO) {
        //所有平台，手机号登陆方式代码一致
        //登录的时候如果没有手机号，则手机号注册成功，自动注册一个user，用户名待填，自动生成一个昵称，密码待填，头像待上传
        //如果已经登录过，则返回那个已经注册的user，根据手机号获取user，返回登录成功
        //记录用户错误日志
        String phoneNum = socialPhoneNumQO.getPhoneNum();
        String authCode = socialPhoneNumQO.getAuthCode();

        //校验验证码，传null用户记录日志
        authenticationManage.checkAuthCode(phoneNum, authCode);

        SocialUserPhoneDO phoneNumDO = socialUserPhoneManage.checkLoginPhoneNum(phoneNum);
        UserDO mineUser;
        if (phoneNumDO != null) {
            mineUser = SocialUserUtil.get(phoneNumDO.getUserId());
        } else {
            mineUser = socialUserManage.createUserByPhoneLogin();
            //创建或返回
            socialUserFansDetailManage.getOrCreateUserFollowDetail(mineUser);
            socialUserPhoneManage.createUserPhoneNum(mineUser, "86", phoneNum);
        }
        SocialMineUserDetailRO userDetailRO = SocialMineUserDetailROFactory.getMineUserDetail(mineUser);

        TokenDO socialUserTokenDO = tokenManage.create(mineUser.getId());

        return new SocialLoginRO<>(socialUserTokenDO.getToken(), userDetailRO);
    }
}
