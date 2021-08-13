package com.socialuni.sdk.utils.model.DO;

import com.socialuni.sdk.exception.SocialNullUserException;
import com.socialuni.entity.model.DO.user.SocialUserFansDetailDO;
import com.socialuni.sdk.repository.SocialUserFansDetailRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserFansDetailUtil {
    private static SocialUserFansDetailRepository socialUserFansDetailRepository;

    @Resource
    public void setSocialUserFansDetailRepository(SocialUserFansDetailRepository socialUserFansDetailRepository) {
        UserFansDetailUtil.socialUserFansDetailRepository = socialUserFansDetailRepository;
    }

    public static SocialUserFansDetailDO getUserFansDetail(Integer userId) {
        SocialUserFansDetailDO socialUserFansDetailDO = socialUserFansDetailRepository.findByUserId(userId);
        if (socialUserFansDetailDO == null) {
            throw new SocialNullUserException();
        }
        return socialUserFansDetailDO;
    }
}
