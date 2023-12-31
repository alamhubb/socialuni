package com.socialuni.social.sdk.im.logic.domain;

import com.socialuni.social.common.api.constant.PlatformType;
import com.socialuni.social.user.sdk.constant.GenderTypeNumEnum;
import com.socialuni.social.user.sdk.constant.SocialuniAccountProviderType;
import com.socialuni.social.user.sdk.logic.entity.SocialBindUserProviderAccountEntity;
import com.socialuni.social.common.sdk.dao.DO.SocialUserPlatformAccountDO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.model.QO.SocialProviderLoginQO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
public class SocialBindUserOpenImAccountDomain {
    @Resource
    SocialBindUserProviderAccountEntity socialBindUserProviderAccountEntity;

    @Transactional
    public SocialUserPlatformAccountDO bindOrUpdateUserOpenImAccount(SocialuniUserDo mineUser, String userUid, String token) {

        SocialProviderLoginQO loginQO = new SocialProviderLoginQO();
        loginQO.setProvider(SocialuniAccountProviderType.openIm);
        loginQO.setPlatform(PlatformType.h5);
        loginQO.setCode(token);

        loginQO.setUnionId(userUid);
        loginQO.setOpenId(userUid);

        loginQO.setNickName(mineUser.getNickname());
        loginQO.setAvatarUrl(mineUser.getAvatar());
        loginQO.setBirthday(mineUser.getBirthday());
        loginQO.setGender(GenderTypeNumEnum.getValueByName(mineUser.getGender()));

        return socialBindUserProviderAccountEntity.bindOrUpdateProviderAccount(mineUser.getUserId(), loginQO);
    }
}