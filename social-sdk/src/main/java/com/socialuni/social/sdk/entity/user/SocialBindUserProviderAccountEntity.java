package com.socialuni.social.sdk.entity.user;

import com.socialuni.social.sdk.manage.SocialUserAccountManage;
import com.socialuni.entity.model.DO.user.UserDO;
import com.socialuni.social.sdk.model.UniUnionIdRO;
import com.socialuni.social.sdk.utils.SocialUniProviderUtil;
import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialBindUserProviderAccountEntity {
    @Resource
    SocialUserAccountManage socialUserAccountManage;


    public void bindProviderAccount(UserDO mineUser, SocialProviderLoginQO loginQO) {
        UniUnionIdRO uniUnionIdRO = SocialUniProviderUtil.getUnionIdRO(loginQO);
        socialUserAccountManage.checkOrCreate(mineUser, loginQO, uniUnionIdRO);
    }
}