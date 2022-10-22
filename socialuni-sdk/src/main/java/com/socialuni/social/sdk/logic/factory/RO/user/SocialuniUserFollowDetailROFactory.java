package com.socialuni.social.sdk.logic.factory.RO.user;

import com.socialuni.social.user.sdk.model.SocialUserFansDetailModel;
import com.socialuni.social.user.sdk.model.SocialuniUserModel;
import com.socialuni.social.sdk.model.RO.user.SocialuniUserFollowDetailRO;
import com.socialuni.social.common.exception.exception.SocialSystemException;
import com.socialuni.social.sdk.logic.factory.ListConvertUtil;
import com.socialuni.social.sdk.dao.redis.UserFansDetailRedis;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class SocialuniUserFollowDetailROFactory {
    static UserFansDetailRedis userFansDetailRedis;

    @Resource
    public void setUserFansDetailRedis(UserFansDetailRedis userFansDetailRedis) {
        SocialuniUserFollowDetailROFactory.userFansDetailRedis = userFansDetailRedis;
    }

    public static SocialuniUserFollowDetailRO newSocialFollowUserRO(SocialuniUserModel user, SocialuniUserModel mineUser) {
        SocialUserFansDetailModel socialUserFansDetailModel = userFansDetailRedis.findUserFansDetailByUserId(user.getUnionId());
        if (socialUserFansDetailModel == null) {
            throw new SocialSystemException("用户粉丝详情未创建");
        }
        return SocialuniUserFollowDetailROFactory.newSocialFollowUserRO(user, mineUser, socialUserFansDetailModel);
    }

    public static SocialuniUserFollowDetailRO newSocialFollowUserRO(SocialuniUserModel user, SocialuniUserModel mineUser, SocialUserFansDetailModel socialUserFansDetailModel) {
        SocialuniUserFollowDetailRO userRO = new SocialuniUserFollowDetailRO(SocialuniContentUserROFactory.newContentUserRO(user, mineUser));
        //用户关注粉丝数
        userRO.setFansNum(socialUserFansDetailModel.getFansNum());
//        if (mineUser != null && userRO.getIsMine()) {
        //非自己也显示关注数量
        userRO.setFollowNum(socialUserFansDetailModel.getFollowNum());
//        }
        return userRO;
    }

    public static List<SocialuniUserFollowDetailRO> getUsers(List<SocialuniUserModel> users, SocialuniUserModel mineUser) {
        return ListConvertUtil.toList(SocialuniUserFollowDetailROFactory::newSocialFollowUserRO, users, mineUser);
    }
}
