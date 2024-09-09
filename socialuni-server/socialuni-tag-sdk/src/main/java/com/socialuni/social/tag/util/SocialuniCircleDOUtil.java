package com.socialuni.social.tag.util;

import com.qingchi.qing.constant.SocialuniCommonStatus;
import com.qingchi.qing.common.exception.base.exception.SocialParamsException;
import com.socialuni.social.tag.dao.DO.SocialuniCircleDO;
import com.socialuni.social.tag.dao.repository.SocialuniCircleRepository;
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
        SocialuniCircleDO circleDO = socialCircleApi.findFirstByNameAndStatus(circleName, SocialuniCommonStatus.enable);
        if (circleDO == null) {
            throw new SocialParamsException("使用了不存在的圈子");
        }
        return circleDO;
    }

    public static SocialuniCircleDO getCircleEnableAllowNull(String circleName) {
        SocialuniCircleDO circleDO = socialCircleApi.findFirstByNameAndStatus(circleName, SocialuniCommonStatus.enable);
        return circleDO;
    }
}
