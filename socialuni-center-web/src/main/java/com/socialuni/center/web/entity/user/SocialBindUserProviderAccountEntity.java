package com.socialuni.center.web.entity.user;

import com.socialuni.center.web.manage.SocialUserAccountManage;
import com.socialuni.center.web.model.UniUnionIdRO;
import com.socialuni.center.web.utils.SocialUniProviderUtil;
import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
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