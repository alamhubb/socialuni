package com.socialuni.social.user.sdk.logic.user;

import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.user.sdk.dao.DO.SocialUserViolationDo;
import com.socialuni.social.user.sdk.dao.repository.SocialUserViolationRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialUserViolationEntity {
    @Resource
    private SocialUserViolationRepository socialUserViolationApi;

    public SocialUserViolationDo getOrCreateViolationDO(Long userId) {
        SocialUserViolationDo SocialUserViolationDo = SocialuniUserUtil.getUserViolationDO(userId);
        if (SocialUserViolationDo == null) {
            SocialUserViolationDo = socialUserViolationApi.savePut(new SocialUserViolationDo(userId));
        }
        return SocialUserViolationDo;
    }
}
