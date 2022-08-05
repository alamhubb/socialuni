package com.socialuni.center.web.entity.user;

import com.socialuni.center.web.repository.user.SocialUserViolationRepository;
import com.socialuni.center.web.utils.SocialUserUtil;
import com.socialuni.center.web.model.DO.user.SocialUserViolationDO;
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
