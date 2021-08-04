package com.socialuni.sdk.factory.user.base;

import com.socialuni.sdk.factory.ListConvertUtil;
import com.socialuni.sdk.model.DO.user.SocialUserFansDetailDO;
import com.socialuni.sdk.model.DO.user.UserDO;
import com.socialuni.sdk.repository.SocialUserFansDetailRepository;
import com.socialuni.social.model.model.RO.user.base.SocialUserFollowDetailRO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class SocialUserFollowDetailROFactory {
    static SocialUserFansDetailRepository socialUserFansDetailRepository;

    @Resource
    public void setUserFollowDetailRepository(SocialUserFansDetailRepository socialUserFansDetailRepository) {
        SocialUserFollowDetailROFactory.socialUserFansDetailRepository = socialUserFansDetailRepository;
    }

    public static SocialUserFollowDetailRO newSocialFollowUserRO(UserDO user, UserDO mineUser) {
        SocialUserFansDetailDO socialUserFansDetailDO = socialUserFansDetailRepository.findByUserId(user.getId());
        return SocialUserFollowDetailROFactory.newSocialFollowUserRO(user, mineUser, socialUserFansDetailDO);
    }

    public static SocialUserFollowDetailRO newSocialFollowUserRO(UserDO user, UserDO mineUser, SocialUserFansDetailDO socialUserFansDetailDO) {
        SocialUserFollowDetailRO userRO = new SocialUserFollowDetailRO(SocialContentUserROFactory.newContentUserRO(user, mineUser));
        //用户关注粉丝数
        userRO.setFansNum(socialUserFansDetailDO.getFansNum());
        if (mineUser != null && userRO.getIsMine()) {
            userRO.setFollowNum(socialUserFansDetailDO.getFollowNum());
        }
        return userRO;
    }

    public static List<SocialUserFollowDetailRO> newUsers(List<UserDO> users, UserDO mineUser) {
        return ListConvertUtil.toList(SocialUserFollowDetailROFactory::newSocialFollowUserRO, users, mineUser);
    }
}
