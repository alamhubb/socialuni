package com.socialuni.social.user.sdk.logic.manage;


import com.socialuni.social.common.sdk.dao.facede.SocialuniUserRepositoryFacede;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.user.sdk.dao.DO.SocialUserPasswordDO;
import com.socialuni.social.user.sdk.model.QO.SocialPhoneAuthCodePasswordQO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class SocialUserPasswordManage {
    public SocialUserPasswordDO getOrCreateUserPasswordDO(Integer userId, String account) {
        SocialUserPasswordDO socialUserPasswordDO = SocialuniUserRepositoryFacede.findByUserId(userId, SocialUserPasswordDO.class);
        if (socialUserPasswordDO == null) {
            socialUserPasswordDO = new SocialUserPasswordDO();
            socialUserPasswordDO.setUserId(userId);
            socialUserPasswordDO.setDevId(DevAccountFacade.getDevIdNotNull());
            socialUserPasswordDO.setAccount(account);
            socialUserPasswordDO = SocialuniUserRepositoryFacede.save(socialUserPasswordDO);
        }
        return socialUserPasswordDO;
    }

    public SocialUserPasswordDO updatePassword(String password, SocialUserPasswordDO socialUserPasswordDO) {
        socialUserPasswordDO.setPassword(password);
        socialUserPasswordDO.setUpdateTime(new Date());
        socialUserPasswordDO = SocialuniUserRepositoryFacede.save(socialUserPasswordDO);
        return socialUserPasswordDO;
    }
}
