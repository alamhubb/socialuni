package com.socialuni.social.sdk.factory.user.base;

import com.socialuni.social.entity.model.DO.user.SocialUserFansDetailDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.exception.SocialSystemException;
import com.socialuni.social.model.model.RO.user.base.SocialUserFollowDetailRO;
import com.socialuni.social.sdk.factory.ListConvertUtil;
import com.socialuni.social.sdk.redis.UserFansDetailRedis;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class SocialUserFollowDetailROFactory {
    static UserFansDetailRedis userFansDetailRedis;

    @Resource
    public void setUserFansDetailRedis(UserFansDetailRedis userFansDetailRedis) {
        SocialUserFollowDetailROFactory.userFansDetailRedis = userFansDetailRedis;
    }

    public static SocialUserFollowDetailRO newSocialFollowUserRO(UserDO user, UserDO mineUser) {
        SocialUserFansDetailDO socialUserFansDetailDO = userFansDetailRedis.findUserFansDetailByUserId(user.getId());
        if (socialUserFansDetailDO == null) {
            throw new SocialSystemException("用户粉丝详情未创建");
        }
        return SocialUserFollowDetailROFactory.newSocialFollowUserRO(user, mineUser, socialUserFansDetailDO);
    }

    public static SocialUserFollowDetailRO newSocialFollowUserRO(UserDO user, UserDO mineUser, SocialUserFansDetailDO socialUserFansDetailDO) {
        SocialUserFollowDetailRO userRO = new SocialUserFollowDetailRO(SocialContentUserROFactory.newContentUserRO(user, mineUser));
        //用户关注粉丝数
        userRO.setFansNum(socialUserFansDetailDO.getFansNum());
//        if (mineUser != null && userRO.getIsMine()) {
        //非自己也显示关注数量
        userRO.setFollowNum(socialUserFansDetailDO.getFollowNum());
//        }
        return userRO;
    }

    public static List<SocialUserFollowDetailRO> newUsers(List<UserDO> users, UserDO mineUser) {
        return ListConvertUtil.toList(SocialUserFollowDetailROFactory::newSocialFollowUserRO, users, mineUser);
    }
}
