package com.socialuni.social.sdk.domain.phone;

import com.socialuni.social.sdk.entity.user.SocialUserPhoneEntity;
import com.socialuni.social.sdk.manage.phone.AuthenticationManage;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.QO.user.SocialPhoneNumQO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

//授权用户信息
@Component
public class SocialBindPhoneNumDomain {
    @Resource
    private AuthenticationManage authenticationManage;
    @Resource
    private SocialUserPhoneEntity socialUserPhoneEntity;

    //  分连个业务，一个是手机号登录，一个是绑定手机号。 绑定手机号的时候，提示手机号已被使用就行了。登录的时候才提示被封禁，这里只是绑定手机号的逻辑
    public UserDO bindPhoneNum(SocialPhoneNumQO socialPhoneNumQO, UserDO mineUser) {
        //登录的时候如果没有手机号，则手机号注册成功，自动注册一个user，用户名待填，自动生成一个昵称，密码待填，头像待上传
        //如果已经登录过，则返回那个已经注册的user，根据手机号获取user，返回登录成功
        //记录用户错误日志
        String authCode = socialPhoneNumQO.getAuthCode();

        String phoneNum = socialPhoneNumQO.getPhoneNum();

        authenticationManage.checkAuthCode(phoneNum, authCode);

        mineUser = socialUserPhoneEntity.checkPhoneNumAndBind(mineUser, "86", phoneNum);
        return mineUser;
    }
}
