package com.socialuni.sdk.factory.user.base;

import com.socialuni.sdk.dao.DO.user.SocialUserFansDetailDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.social.web.sdk.exception.SocialSystemException;
import com.socialuni.sdk.model.RO.user.base.SocialUserFollowDetailRO;
import com.socialuni.sdk.factory.ListConvertUtil;
import com.socialuni.sdk.dao.redis.UserFansDetailRedis;
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

    public static SocialUserFollowDetailRO newSocialFollowUserRO(SocialuniUserDO user, SocialuniUserDO mineUser) {
        SocialUserFansDetailDO socialUserFansDetailDO = userFansDetailRedis.findUserFansDetailByUserId(user.getUnionId());
        if (socialUserFansDetailDO == null) {
            throw new SocialSystemException("用户粉丝详情未创建");
        }
        return SocialUserFollowDetailROFactory.newSocialFollowUserRO(user, mineUser, socialUserFansDetailDO);
    }

    public static SocialUserFollowDetailRO newSocialFollowUserRO(SocialuniUserDO user, SocialuniUserDO mineUser, SocialUserFansDetailDO socialUserFansDetailDO) {
        SocialUserFollowDetailRO userRO = new SocialUserFollowDetailRO(SocialContentUserROFactory.newContentUserRO(user, mineUser));
        //用户关注粉丝数
        userRO.setFansNum(socialUserFansDetailDO.getFansNum());
//        if (mineUser != null && userRO.getIsMine()) {
        //非自己也显示关注数量
        userRO.setFollowNum(socialUserFansDetailDO.getFollowNum());
//        }
        return userRO;
    }

    public static List<SocialUserFollowDetailRO> newUsers(List<SocialuniUserDO> users, SocialuniUserDO mineUser) {
        return ListConvertUtil.toList(SocialUserFollowDetailROFactory::newSocialFollowUserRO, users, mineUser);
    }
}
