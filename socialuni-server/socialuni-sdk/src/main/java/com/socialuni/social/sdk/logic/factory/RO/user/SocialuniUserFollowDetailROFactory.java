package com.socialuni.social.sdk.logic.factory.RO.user;

import com.socialuni.social.common.api.model.user.SocialuniUserFollowDetailRO;
import com.socialuni.social.common.sdk.utils.ListConvertUtil;
import com.socialuni.social.sdk.logic.manage.SocialUserFansDetailManage;
import com.socialuni.social.user.sdk.model.DO.SocialUserFansDetailDo;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserDo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class SocialuniUserFollowDetailROFactory {
    static SocialUserFansDetailManage socialUserFansDetailManage;

    @Resource
    public void setSocialUserFansDetailManage(SocialUserFansDetailManage socialUserFansDetailManage) {
        SocialuniUserFollowDetailROFactory.socialUserFansDetailManage = socialUserFansDetailManage;
    }

    public static SocialuniUserFollowDetailRO newSocialFollowUserRO(SocialuniUserDo user, SocialuniUserDo mineUser) {
        SocialUserFansDetailDo SocialUserFansDetailDo = socialUserFansDetailManage.getOrCreateUserFollowDetail(user);

        return SocialuniUserFollowDetailROFactory.newSocialFollowUserRO(user, mineUser, SocialUserFansDetailDo);
    }

    public static SocialuniUserFollowDetailRO newSocialFollowUserRO(SocialuniUserDo user, SocialuniUserDo mineUser, SocialUserFansDetailDo SocialUserFansDetailDo) {
        SocialuniUserFollowDetailRO userRO = new SocialuniUserFollowDetailRO(SocialuniContentUserROFactory.newContentUserRO(user, mineUser));
        //用户关注粉丝数
        userRO.setFansNum(SocialUserFansDetailDo.getFansNum());
//        if (mineUser != null && userRO.getIsMine()) {
        //非自己也显示关注数量
        userRO.setFollowNum(SocialUserFansDetailDo.getFollowNum());
//        }
        return userRO;
    }

    public static List<SocialuniUserFollowDetailRO> getUsers(List<SocialuniUserDo> users, SocialuniUserDo mineUser) {
        return ListConvertUtil.toList(SocialuniUserFollowDetailROFactory::newSocialFollowUserRO, users, mineUser);
    }
}
