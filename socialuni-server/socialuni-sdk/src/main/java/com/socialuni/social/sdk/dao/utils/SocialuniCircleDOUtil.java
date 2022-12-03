package com.socialuni.social.sdk.dao.utils;

import com.socialuni.social.common.api.enumeration.CommonStatus;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.community.sdk.entity.SocialuniCircleDO;
import com.socialuni.social.community.sdk.repository.SocialuniCircleRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialuniCircleDOUtil {
    private static SocialuniCircleRepository socialCircleApi;

    @Resource
    public void setSocialCircleRepository(SocialuniCircleRepository socialCircleApi) {
        SocialuniCircleDOUtil.socialCircleApi = socialCircleApi;
    }

    public static SocialuniCircleDO getCircleEnableNotNull(String circleName) {
        SocialuniCircleDO circleDO = socialCircleApi.findFirstByNameAndStatus(circleName, CommonStatus.enable);
        if (circleDO == null) {
            throw new SocialParamsException("使用了不存在的圈子");
        }
        return circleDO;
    }

    public static SocialuniCircleDO getCircleEnableAllowNull(String circleName) {
        SocialuniCircleDO circleDO = socialCircleApi.findFirstByNameAndStatus(circleName, CommonStatus.enable);
        return circleDO;
    }
}
