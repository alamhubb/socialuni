package com.socialuni.social.sdk.dao.utils;

import com.socialuni.social.common.sdk.enumeration.CommonStatus;
import com.socialuni.social.community.sdk.model.SocialuniCircleModel;
import com.socialuni.social.community.sdk.api.SocialCircleInterface;
import com.socialuni.social.common.sdk.exception.exception.SocialParamsException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialuniCircleDOUtil {
    private static SocialCircleInterface socialCircleApi;

    @Resource
    public void setSocialCircleRepository(SocialCircleInterface socialCircleApi) {
        SocialuniCircleDOUtil.socialCircleApi = socialCircleApi;
    }

    public static SocialuniCircleModel getCircleEnableNotNull(String circleName) {
        SocialuniCircleModel circleDO = socialCircleApi.findFirstByNameAndStatus(circleName, CommonStatus.enable);
        if (circleDO == null) {
            throw new SocialParamsException("使用了不存在的圈子");
        }
        return circleDO;
    }
}
