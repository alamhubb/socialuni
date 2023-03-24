package com.socialuni.social.sdk.logic.entity;

import com.socialuni.social.user.sdk.logic.entity.SocialPhoneLoginEntity;
import com.socialuni.social.sdk.logic.manage.SocialUserFansDetailManage;
import com.socialuni.social.common.sdk.dao.DO.keywords.SocialuniUserDo;
import com.socialuni.social.user.sdk.model.QO.SocialPhoneNumQO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
public class SocialUserDetailLoginEntity {
    @Resource
    SocialPhoneLoginEntity socialPhoneLoginEntity;
    @Resource
    SocialUserFansDetailManage socialUserFansDetailManage;

    //1.通过联盟应用输入手机号，登录调用
    //2.不接入联盟，自己应用手机号登录
    @Transactional
    public SocialuniUserDo phoneLogin(SocialPhoneNumQO socialPhoneNumQO) {
        SocialuniUserDo mineUser = socialPhoneLoginEntity.phoneLogin(socialPhoneNumQO);
        socialUserFansDetailManage.getOrCreateUserFollowDetail(mineUser);

        return mineUser;
    }
}
