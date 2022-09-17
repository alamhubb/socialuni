package com.socialuni.sdk.utils.model.DO;

import com.socialuni.sdk.constant.socialuni.CommonStatus;
import com.socialuni.sdk.dao.DO.circle.SocialuniCircleDO;
import com.socialuni.sdk.dao.repository.community.SocialCircleRepository;
import com.socialuni.social.web.sdk.exception.SocialParamsException;
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
