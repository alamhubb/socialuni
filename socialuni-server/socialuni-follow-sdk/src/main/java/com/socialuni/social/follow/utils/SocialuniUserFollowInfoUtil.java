package com.socialuni.social.follow.utils;

import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.follow.dao.DO.SocialUserFansDetailDo;
import com.socialuni.social.follow.logic.manage.SocialUserFansDetailManage;
import com.socialuni.social.follow.logic.manage.SocialuniUserFollowManage;
import com.socialuni.social.follow.model.follow.SocialuniUserFansDetailRO;
import com.socialuni.social.follow.model.follow.SocialuniUserFollowRelationInfoRO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialuniUserFollowInfoUtil {

    static SocialUserFansDetailManage socialUserFansDetailManage;

    @Resource
    public void setSocialUserFansDetailManage(SocialUserFansDetailManage socialUserFansDetailManage) {
        SocialuniUserFollowInfoUtil.socialUserFansDetailManage = socialUserFansDetailManage;
    }

    static SocialuniUserFollowManage followManage;

    @Resource
    public void setFollowManage(SocialuniUserFollowManage followManage) {
        SocialuniUserFollowInfoUtil.followManage = followManage;
    }

    public static SocialuniUserFansDetailRO getMineUserFollowRelationInfo() {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();
        return SocialuniUserFollowInfoUtil.getUserFollowRelationInfo(mineUser);
    }


    public static SocialuniUserFansDetailRO getUserFollowRelationInfo(Long userId) {
        SocialuniUserDo user = SocialuniUserUtil.getUserByUuid(userId);
        return getUserFollowRelationInfo(user);
    }

    public static SocialuniUserFansDetailRO getUserFollowRelationInfo(SocialuniUserDo user) {
        SocialUserFansDetailDo socialUserFansDetailDo = socialUserFansDetailManage.getOrCreateUserFollowDetail(user);
        SocialuniUserFansDetailRO userRO = new SocialuniUserFansDetailRO();
        //用户关注粉丝数
        userRO.setFansNum(socialUserFansDetailDo.getFansNum());
//        if (mineUser != null && userRO.getIsMine()) {
        //非自己也显示关注数量
        userRO.setFollowNum(socialUserFansDetailDo.getFollowNum());
//        }
        return userRO;
    }

    public static SocialuniUserFollowRelationInfoRO getUserFollowRelationInfo(Long userId, Long mineUserId) {
        SocialuniUserFollowRelationInfoRO userRO = new SocialuniUserFollowRelationInfoRO();

        userRO.setHasFollowed(false);
        userRO.setHasBeFollowed(false);

        if (mineUserId == null) {
            return userRO;
        }

        if (userId.equals(mineUserId)) {
            return userRO;
        }

        boolean hasFollowUser = followManage.userHasFollowBeUser(mineUserId, userId);
        userRO.setHasFollowed(hasFollowUser);

        boolean hasBeFollowed = followManage.userHasFollowBeUser(userId, mineUserId);
        userRO.setHasBeFollowed(hasBeFollowed);

        return userRO;
    }


    public static boolean usersIsFriend(Long userId, Long mineUserId) {
        SocialuniUserFollowRelationInfoRO socialuniUserFollowRelationInfoRO = SocialuniUserFollowInfoUtil.getUserFollowRelationInfo(userId, mineUserId);

        return socialuniUserFollowRelationInfoRO.getHasBeFollowed() && socialuniUserFollowRelationInfoRO.getHasFollowed();
    }
}
