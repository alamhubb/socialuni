package com.socialuni.sdk.factory.RO.user;

import com.socialuni.sdk.model.RO.user.CenterContentUserRO;
import com.socialuni.sdk.model.RO.user.CenterUserFollowDetailRO;
import com.socialuni.sdk.factory.ListConvertUtil;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.model.RO.user.base.SocialUserFollowDetailRO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CenterUserFollowDetailROFactory {
    public static CenterUserFollowDetailRO getUserFollowDetailRO(SocialUserFollowDetailRO socialUserRO, SocialuniUserDO mineUser) {
        CenterContentUserRO centerContentUserRO = CenterContentUserROFactory.getContentUserRO(socialUserRO, mineUser);

        CenterUserFollowDetailRO centerUserFollowDetailRO = new CenterUserFollowDetailRO(centerContentUserRO, socialUserRO);
        return centerUserFollowDetailRO;
    }

    public static List<CenterUserFollowDetailRO> getUsers(List<SocialUserFollowDetailRO> users, SocialuniUserDO mineUser) {
        return ListConvertUtil.toList(CenterUserFollowDetailROFactory::getUserFollowDetailRO, users, mineUser);
    }
}
