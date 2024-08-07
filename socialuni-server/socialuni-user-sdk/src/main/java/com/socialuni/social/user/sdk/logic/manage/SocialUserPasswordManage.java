package com.socialuni.social.user.sdk.logic.manage;


import com.socialuni.social.common.sdk.dao.facede.SocialuniUserRepositoryFacede;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.user.sdk.dao.DO.SocialUserPasswordDO;
import com.socialuni.social.user.sdk.model.QO.SocialPhoneAuthCodePasswordQO;
import com.socialuni.social.user.sdk.repository.SocialuniUserPasswordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;

@Slf4j
@Component
public class SocialUserPasswordManage {
    @Resource
    SocialuniUserPasswordRepository socialuniUserPasswordRepository;

    public SocialUserPasswordDO getOrCreateUserPasswordDO(Integer userId, String account, String password) {
        SocialUserPasswordDO socialUserPasswordDO = SocialuniUserRepositoryFacede.findByUserId(userId, SocialUserPasswordDO.class);
        if (socialUserPasswordDO == null) {
            socialUserPasswordDO = new SocialUserPasswordDO();
            socialUserPasswordDO.setUserId(userId);
            socialUserPasswordDO.setDevId(DevAccountFacade.getDevIdNotNull());
            socialUserPasswordDO.setAccount(account);
            socialUserPasswordDO.setPassword(password);
            socialUserPasswordDO.setErrorNum(0);
            socialUserPasswordDO.setLastErrorTime(socialUserPasswordDO.getUpdateTime());
            socialUserPasswordDO = SocialuniUserRepositoryFacede.save(socialUserPasswordDO);
        }
        return socialUserPasswordDO;
    }

    public SocialUserPasswordDO updatePassword(String password, SocialUserPasswordDO socialUserPasswordDO) {
        socialUserPasswordDO.setPassword(password);
        socialUserPasswordDO.setUpdateTime(new Date());
        socialUserPasswordDO.setErrorNum(0);
        socialUserPasswordDO.setLastErrorTime(socialUserPasswordDO.getUpdateTime());
        socialUserPasswordDO = SocialuniUserRepositoryFacede.save(socialUserPasswordDO);
        return socialUserPasswordDO;
    }
}
