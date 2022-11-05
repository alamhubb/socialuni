package com.socialuni.social.sdk.logic.domain.user;

import com.socialuni.social.common.api.utils.RequestUtil;
import com.socialuni.social.im.contrller.SocialuniOpenImUserFeign;
import com.socialuni.social.sdk.constant.SocialuniAccountProviderType;
import com.socialuni.social.sdk.logic.entity.user.SocialBindUserProviderAccountEntity;
import com.socialuni.social.sdk.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.user.sdk.entity.SocialUserAccountDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
public class SocialBindUserOpenImAccountDomain {
    @Resource
    SocialBindUserProviderAccountEntity socialBindUserProviderAccountEntity;
    @Resource
    SocialuniOpenImUserFeign socialuniOpenImUserFeign;

    @Transactional
    public SocialUserAccountDO bindUserOpenImAccount(Integer mineUserId, String userUid) {
        String token = socialuniOpenImUserFeign.getToken(userUid);

        SocialProviderLoginQO loginQO = new SocialProviderLoginQO();
        loginQO.setProvider(SocialuniAccountProviderType.openIm);
        loginQO.setPlatform(RequestUtil.getPlatform());
        loginQO.setCode(token);

        loginQO.setUnionId(userUid);
        loginQO.setOpenId(userUid);

        return socialBindUserProviderAccountEntity.bindProviderAccount(mineUserId, loginQO);
    }
}