package com.socialuni.sdk.logic.entity.user;

import com.socialuni.sdk.logic.manage.SocialUserAccountManage;
import com.socialuni.sdk.model.UniUnionIdRO;
import com.socialuni.sdk.utils.SocialUniProviderUtil;
import com.socialuni.sdk.model.QO.user.SocialProviderLoginQO;
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