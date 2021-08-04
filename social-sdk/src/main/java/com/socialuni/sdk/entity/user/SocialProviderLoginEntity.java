package com.socialuni.sdk.entity.user;

import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import com.socialuni.sdk.manage.SocialUserFansDetailManage;
import com.socialuni.sdk.model.DO.user.UserDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialProviderLoginEntity {
    @Resource
    SocialUserCreateEntity socialUserCreateEntity;
    @Resource
    private SocialUserFansDetailManage socialUserFansDetailManage;


    //根据渠道登录信息获取user，支持social比commonUserDomain
    //这个单独出来是因为区分了基础provider和社交，这个单独增加了对社交渠道的支持
    public UserDO providerLogin(SocialProviderLoginQO loginQO) {
        //创建或返回
        UserDO mineUser = socialUserCreateEntity.getOrCreateUserEntity(loginQO);
        return mineUser;
    }
}
