package com.socialuni.sdk.entity.user;

import com.socialuni.sdk.manage.SocialUserFansDetailManage;
import com.socialuni.sdk.manage.SocialUserManage;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.QO.user.SocialProviderLoginQO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialUserEntity {
    @Resource
    private SocialUserFansDetailManage socialUserFansDetailManage;
    @Resource
    private SocialUserManage socialUserManage;

    //根据渠道登录信息获取user，支持social比commonUserDomain
    //这个单独出来是因为区分了基础provider和社交，这个单独增加了对社交渠道的支持
    public SocialUserDO createUserAndDetail(SocialProviderLoginQO loginQO) {
        SocialUserDO mineUser = socialUserManage.createUserByProviderLogin(loginQO);
        //创建或返回
        socialUserFansDetailManage.getOrCreateUserFollowDetail(mineUser);
        return mineUser;
    }
}
