package com.socialuni.social.sdk.utils.model.DO;

import com.socialuni.social.exception.SocialNullUserException;
import com.socialuni.social.entity.model.DO.user.SocialUserFansDetailDO;
import com.socialuni.social.sdk.repository.SocialUserFansDetailRepository;
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
