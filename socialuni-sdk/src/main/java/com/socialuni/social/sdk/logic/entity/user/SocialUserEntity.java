package com.socialuni.social.sdk.logic.entity.user;

import com.socialuni.social.sdk.logic.manage.SocialUserFansDetailManage;
import com.socialuni.social.sdk.logic.manage.SocialUserManage;
import com.socialuni.social.user.sdk.model.SocialuniUserDO;
import com.socialuni.social.sdk.model.QO.user.SocialProviderLoginQO;
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
    public SocialuniUserDO createUserAndDetail(SocialProviderLoginQO loginQO) {
        SocialuniUserDO mineUser = socialUserManage.createUserByProviderLogin(loginQO);
        //创建或返回
        socialUserFansDetailManage.getOrCreateUserFollowDetail(mineUser);
        return mineUser;
    }
}
