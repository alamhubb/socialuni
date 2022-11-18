package com.socialuni.social.sdk.dao.utils;

import com.socialuni.social.common.api.enumeration.CommonStatus;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.community.sdk.entity.SocialuniCircleDO;
import com.socialuni.social.community.sdk.repository.SocialCircleRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialuniCircleDOUtil {
    private static SocialCircleRepository socialCircleApi;

    @Resource
    public void setSocialCircleRepository(SocialCircleRepository socialCircleApi) {
        SocialuniCircleDOUtil.socialCircleApi = socialCircleApi;
    }

    public static SocialuniCircleDO getCircleEnableNotNull(String circleName) {
        SocialuniCircleDO circleDO = socialCircleApi.findFirstByNameAndStatus(circleName, CommonStatus.enable);
        if (circleDO == null) {
            throw new SocialParamsException("使用了不存在的圈子");
        }
        return circleDO;
    }
}
