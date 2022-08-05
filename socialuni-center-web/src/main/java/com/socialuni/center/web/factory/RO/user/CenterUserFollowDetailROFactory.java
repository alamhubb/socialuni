package com.socialuni.center.web.factory.RO.user;

import com.socialuni.center.web.model.RO.user.CenterContentUserRO;
import com.socialuni.center.web.model.RO.user.CenterUserFollowDetailRO;
import com.socialuni.center.web.factory.ListConvertUtil;
import com.socialuni.center.web.model.DO.user.SocialUserDO;
import com.socialuni.center.web.model.RO.user.base.SocialUserFollowDetailRO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CenterUserFollowDetailROFactory {
    public static CenterUserFollowDetailRO getUserFollowDetailRO(SocialUserFollowDetailRO socialUserRO, SocialUserDO mineUser) {
        CenterContentUserRO centerContentUserRO = CenterContentUserROFactory.getContentUserRO(socialUserRO, mineUser);

        CenterUserFollowDetailRO centerUserFollowDetailRO = new CenterUserFollowDetailRO(centerContentUserRO, socialUserRO);
        return centerUserFollowDetailRO;
    }

    public static List<CenterUserFollowDetailRO> getUsers(List<SocialUserFollowDetailRO> users, SocialUserDO mineUser) {
        return ListConvertUtil.toList(CenterUserFollowDetailROFactory::getUserFollowDetailRO, users, mineUser);
    }
}
