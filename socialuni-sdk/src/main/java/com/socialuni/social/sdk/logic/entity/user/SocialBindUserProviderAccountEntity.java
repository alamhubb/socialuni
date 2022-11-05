package com.socialuni.social.sdk.logic.entity.user;

import com.socialuni.social.sdk.logic.manage.SocialUserAccountManage;
import com.socialuni.social.sdk.model.UniUnionIdRO;
import com.socialuni.social.sdk.utils.SocialUniProviderUtil;
import com.socialuni.social.sdk.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.user.sdk.entity.SocialUserAccountDO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialBindUserProviderAccountEntity {
    @Resource
    SocialUserAccountManage socialUserAccountManage;

    public SocialUserAccountDO bindOrUpdateProviderAccount(Integer mineUserId, SocialProviderLoginQO loginQO) {
        UniUnionIdRO uniUnionIdRO = SocialUniProviderUtil.getUnionIdRO(loginQO);
        return socialUserAccountManage.checkOrCreateOrUpdate(mineUserId, loginQO, uniUnionIdRO);
    }
}