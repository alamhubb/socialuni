package com.socialuni.social.sdk.logic.entity.user;

import com.socialuni.social.sdk.logic.manage.SocialUserAccountManage;
import com.socialuni.social.sdk.model.UniUnionIdRO;
import com.socialuni.social.sdk.utils.SocialUniProviderUtil;
import com.socialuni.social.sdk.model.QO.user.SocialProviderLoginQO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialBindUserProviderAccountEntity {
    @Resource
    SocialUserAccountManage socialUserAccountManage;

    public void bindProviderAccount(Integer mineUserId, SocialProviderLoginQO loginQO) {
        UniUnionIdRO uniUnionIdRO = SocialUniProviderUtil.getUnionIdRO(loginQO);
        socialUserAccountManage.checkOrCreate(mineUserId, loginQO, uniUnionIdRO);
    }
}