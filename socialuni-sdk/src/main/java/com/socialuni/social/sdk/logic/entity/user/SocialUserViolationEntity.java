package com.socialuni.social.sdk.logic.entity.user;

import com.socialuni.social.sdk.dao.repository.user.SocialUserViolationRepository;
import com.socialuni.social.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.sdk.dao.DO.user.SocialUserViolationDO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialUserViolationEntity {
    @Resource
    private SocialUserViolationRepository socialUserViolationRepository;

    public SocialUserViolationDO getOrCreateViolationDO(Integer userId) {
        SocialUserViolationDO socialUserViolationDO = SocialuniUserUtil.getUserViolationDO(userId);
        if (socialUserViolationDO == null) {
            socialUserViolationDO = socialUserViolationRepository.save(new SocialUserViolationDO(userId));
        }
        return socialUserViolationDO;
    }
}
