package com.socialuni.center.web.factory.RO.user;

import com.socialuni.api.model.RO.user.CenterContentUserRO;
import com.socialuni.api.model.RO.user.CenterUserFollowDetailRO;
import com.socialuni.center.web.factory.ListConvertUtil;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.RO.user.base.SocialUserFollowDetailRO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CenterUserFollowDetailROFactory {
    public static CenterUserFollowDetailRO getUserFollowDetailRO(SocialUserFollowDetailRO socialUserRO, UserDO mineUser) {
        CenterContentUserRO centerContentUserRO = CenterContentUserROFactory.getContentUserRO(socialUserRO, mineUser);

        CenterUserFollowDetailRO centerUserFollowDetailRO = new CenterUserFollowDetailRO(centerContentUserRO, socialUserRO);
        return centerUserFollowDetailRO;
    }

    public static List<CenterUserFollowDetailRO> getUsers(List<SocialUserFollowDetailRO> users, UserDO mineUser) {
        return ListConvertUtil.toList(CenterUserFollowDetailROFactory::getUserFollowDetailRO, users, mineUser);
    }
}
