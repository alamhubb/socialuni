package com.socialuni.social.sdk.logic.domain.phone;

import com.socialuni.social.user.sdk.logic.entity.SocialUserPhoneEntity;
import com.socialuni.social.user.sdk.logic.manage.SocialuniAuthenticationManage;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.model.QO.SocialPhoneNumAuthCodeQO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

//授权用户信息
@Component
public class SocialBindPhoneNumDomain {
    @Resource
    private SocialuniAuthenticationManage authenticationManage;
    @Resource
    private SocialUserPhoneEntity socialUserPhoneEntity;

    //  分连个业务，一个是手机号登录，一个是绑定手机号。 绑定手机号的时候，提示手机号已被使用就行了。登录的时候才提示被封禁，这里只是绑定手机号的逻辑
    public String bindPhoneNum(SocialPhoneNumAuthCodeQO socialPhoneNumQO, SocialuniUserDo mineUser) {
        //登录的时候如果没有手机号，则手机号注册成功，自动注册一个user，用户名待填，自动生成一个昵称，密码待填，头像待上传
        //如果已经登录过，则返回那个已经注册的user，根据手机号获取user，返回登录成功
        //记录用户错误日志
        String authCode = socialPhoneNumQO.getAuthCode();

        String phoneNum = socialPhoneNumQO.getPhoneNum();

        authenticationManage.checkAuthCode(phoneNum, authCode);

        mineUser = socialUserPhoneEntity.checkPhoneNumAndCreateBind(mineUser, "86", phoneNum);

        //根据用户得到返回详情
//        SocialuniMineUserDetailRO socialMineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(mineUser);

        //返回真实手机号
//        socialMineUserDetailRO.setPhoneNum(phoneNum);

        return phoneNum;
    }
}
