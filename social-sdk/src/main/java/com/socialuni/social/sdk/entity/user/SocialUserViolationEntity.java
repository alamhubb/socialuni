package com.socialuni.social.sdk.entity.user;

import com.socialuni.social.entity.model.DO.user.SocialUserViolationDO;
import com.socialuni.social.sdk.repository.user.SocialUserViolationRepository;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialUserViolationEntity {
    @Resource
    private SocialUserViolationRepository socialUserViolationRepository;

    public SocialUserViolationDO getOrCreateViolationDO(Integer userId) {
        SocialUserViolationDO socialUserViolationDO = SocialUserUtil.getUserViolationDO(userId);
        if (socialUserViolationDO == null) {
            socialUserViolationDO = socialUserViolationRepository.save(new SocialUserViolationDO(userId));
        }
        return socialUserViolationDO;
    }
}
