package com.socialuni.sdk.entity.user;

import com.socialuni.sdk.manage.phone.AuthenticationManage;
import com.socialuni.sdk.manage.phone.SocialUserPhoneManage;
import com.socialuni.sdk.repository.dev.ThirdUserRepository;
import com.socialuni.sdk.utils.SocialUserUtil;
import com.socialuni.sdk.model.DO.user.SocialUserPhoneDO;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.QO.user.SocialPhoneNumQO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
public class SocialPhoneLoginEntity {
    @Resource
    SocialUserPhoneManage socialUserPhoneManage;
    @Resource
    AuthenticationManage authenticationManage;
    @Resource
    SocialUserPhoneEntity socialUserPhoneEntity;

    @Resource
    ThirdUserRepository thirdUserRepository;


    //1.通过联盟应用输入手机号，登录调用
    //2.不接入联盟，自己应用手机号登录
    @Transactional
    public SocialUserDO phoneLogin(SocialPhoneNumQO socialPhoneNumQO) {
        //所有平台，手机号登陆方式代码一致
        //登录的时候如果没有手机号，则手机号注册成功，自动注册一个user，用户名待填，自动生成一个昵称，密码待填，头像待上传
        //如果已经登录过，则返回那个已经注册的user，根据手机号获取user，返回登录成功
        //记录用户错误日志
        String phoneNum = socialPhoneNumQO.getPhoneNum();
        String authCode = socialPhoneNumQO.getAuthCode();

        //校验验证码，传null用户记录日志
        authenticationManage.checkAuthCode(phoneNum, authCode);

        SocialUserPhoneDO socialUserPhoneDO = socialUserPhoneManage.checkLoginPhoneNum(phoneNum);

        SocialUserDO mineUser;
        if (socialUserPhoneDO != null) {
            mineUser = SocialUserUtil.getNotNull(socialUserPhoneDO.getUserId());
        } else {
            mineUser = socialUserPhoneEntity.createUserPhoneEntity(phoneNum);
        }
        return mineUser;
    }
}
