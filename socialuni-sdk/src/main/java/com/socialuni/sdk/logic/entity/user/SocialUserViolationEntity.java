package com.socialuni.sdk.logic.entity.user;

import com.socialuni.sdk.dao.repository.user.SocialUserViolationRepository;
import com.socialuni.sdk.utils.SocialuniUserUtil;
import com.socialuni.sdk.dao.DO.user.SocialUserViolationDO;
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
