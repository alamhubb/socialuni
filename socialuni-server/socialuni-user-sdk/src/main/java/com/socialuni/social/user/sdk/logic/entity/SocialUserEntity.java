package com.socialuni.social.user.sdk.logic.entity;

import com.socialuni.social.user.sdk.logic.manage.SocialUserManage;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.model.QO.SocialProviderLoginQO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialUserEntity {
    @Resource
    private SocialUserManage socialUserManage;

    //根据渠道登录信息获取user，支持social比commonUserDomain
    //这个单独出来是因为区分了基础provider和社交，这个单独增加了对社交渠道的支持
    public SocialuniUserDo createUserAndDetail(SocialProviderLoginQO loginQO) {
        SocialuniUserDo mineUser = socialUserManage.createUserByProviderLogin(loginQO);
        return mineUser;
    }
}
