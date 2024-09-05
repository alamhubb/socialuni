package com.socialuni.social.user.sdk.logic.entity;

import com.socialuni.social.common.api.utils.SnowflakeIdUtil;
import com.socialuni.social.user.sdk.logic.manage.SocialuniAuthenticationManage;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.model.QO.SocialPhoneNumAuthCodeQO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
public class SocialPhoneLoginEntity {
    @Resource
    SocialuniAuthenticationManage authenticationManage;
    @Resource
    SocialUserEntity socialUserEntity;

    //1.通过联盟应用输入手机号，登录调用
    //2.不接入联盟，自己应用手机号登录
    @Transactional
    public SocialuniUserDo phoneLogin(SocialPhoneNumAuthCodeQO socialPhoneNumQO) {
        //所有平台，手机号登陆方式代码一致
        //登录的时候如果没有手机号，则手机号注册成功，自动注册一个user，用户名待填，自动生成一个昵称，密码待填，头像待上传
        //如果已经登录过，则返回那个已经注册的user，根据手机号获取user，返回登录成功
        //记录用户错误日志
        String phoneNum = socialPhoneNumQO.getPhoneNum();
        String authCode = socialPhoneNumQO.getAuthCode();

        //校验验证码，传null用户记录日志
        authenticationManage.checkAuthCode(phoneNum, authCode);

        SocialuniUserDo mineUser = socialUserEntity.getOrCreateUserByPhoneNum(phoneNum, SnowflakeIdUtil.nextId());

        return mineUser;
    }
}
