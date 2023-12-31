package com.socialuni.social.user.sdk.logic.entity;

import com.socialuni.social.user.sdk.logic.manage.SocialUserAccountManage;
import com.socialuni.social.user.sdk.model.RO.UniUnionIdRO;
import com.socialuni.social.user.sdk.utils.SocialUniProviderUtil;
import com.socialuni.social.user.sdk.model.QO.SocialProviderLoginQO;
import com.socialuni.social.common.sdk.dao.DO.SocialUserPlatformAccountDO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialBindUserProviderAccountEntity {
    @Resource
    SocialUserAccountManage socialUserAccountManage;

    public SocialUserPlatformAccountDO bindOrUpdateProviderAccount(Integer mineUserId, SocialProviderLoginQO loginQO) {
        UniUnionIdRO uniUnionIdRO = SocialUniProviderUtil.getUnionIdRO(loginQO);
        return socialUserAccountManage.checkOrCreateOrUpdate(mineUserId, loginQO, uniUnionIdRO);
    }
}