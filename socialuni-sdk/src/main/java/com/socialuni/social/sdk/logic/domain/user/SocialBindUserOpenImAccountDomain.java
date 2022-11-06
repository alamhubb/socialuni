package com.socialuni.social.sdk.logic.domain.user;

import com.socialuni.social.sdk.constant.GenderTypeNumEnum;
import com.socialuni.social.sdk.constant.SocialuniAccountProviderType;
import com.socialuni.social.sdk.logic.entity.user.SocialBindUserProviderAccountEntity;
import com.socialuni.social.sdk.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.user.sdk.entity.SocialUserAccountDO;
import com.socialuni.social.user.sdk.entity.SocialuniUserDo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
public class SocialBindUserOpenImAccountDomain {
    @Resource
    SocialBindUserProviderAccountEntity socialBindUserProviderAccountEntity;

    @Transactional
    public SocialUserAccountDO bindOrUpdateUserOpenImAccount(SocialuniUserDo mineUser, String userUid, String token) {

        SocialProviderLoginQO loginQO = new SocialProviderLoginQO();
        loginQO.setProvider(SocialuniAccountProviderType.openIm);
//        loginQO.setPlatform(RequestUtil.getPlatform());
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