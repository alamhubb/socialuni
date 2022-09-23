package com.socialuni.social.sdk.dao.utils;

import com.socialuni.social.sdk.constant.socialuni.CommonStatus;
import com.socialuni.social.sdk.dao.DO.circle.SocialuniCircleDO;
import com.socialuni.social.sdk.dao.repository.community.SocialCircleRepository;
import com.socialuni.social.common.exception.exception.SocialParamsException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialuniCircleDOUtil {
    private static SocialCircleRepository socialCircleRepository;

    @Resource
    public void setSocialCircleRepository(SocialCircleRepository socialCircleRepository) {
        SocialuniCircleDOUtil.socialCircleRepository = socialCircleRepository;
    }

    public static SocialuniCircleDO getCircleEnableNotNull(String circleName) {
        SocialuniCircleDO circleDO = socialCircleRepository.findFirstByNameAndStatus(circleName, CommonStatus.enable);
        if (circleDO == null) {
            throw new SocialParamsException("使用了不存在的圈子");
        }
        return circleDO;
    }
}
