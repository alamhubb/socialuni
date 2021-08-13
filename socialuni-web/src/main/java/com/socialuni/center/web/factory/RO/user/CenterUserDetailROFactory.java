package com.socialuni.center.web.factory.RO.user;

import com.socialuni.api.model.RO.user.CenterUserDetailRO;
import com.socialuni.api.model.RO.user.CenterUserFollowDetailRO;
import com.socialuni.api.model.RO.user.CenterUserImgRO;
import com.socialuni.sdk.factory.user.base.SocialUserDetailROFactory;
import com.socialuni.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.RO.user.SocialUserDetailRO;
import com.socialuni.social.model.model.RO.user.UserImgRO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CenterUserDetailROFactory {

    public static CenterUserDetailRO getUserDetailRO(UserDO detailUser, UserDO mineUser) {
        //user基础信息
        SocialUserDetailRO socialUserRO = SocialUserDetailROFactory.getUserDetailRO(detailUser, mineUser);

        CenterUserDetailRO centerUserDetailRO = CenterUserDetailROFactory.getUserDetailRO(socialUserRO, mineUser);

        return centerUserDetailRO;
    }

    public static CenterUserDetailRO getUserDetailRO(SocialUserDetailRO socialUserRO, UserDO mineUser) {
        //user基础信息
        CenterUserFollowDetailRO centerUserFollowDetailRO = CenterUserFollowDetailROFactory.getUserFollowDetailRO(socialUserRO, mineUser);

        CenterUserDetailRO centerUserDetailRO = new CenterUserDetailRO(centerUserFollowDetailRO);
        List<UserImgRO> imgVOS = socialUserRO.getImgs();

        List<CenterUserImgRO> imgROES = CenterUserImgROFactory.getImgs(imgVOS, mineUser);

        centerUserDetailRO.setImgs(imgROES);

        return centerUserDetailRO;
    }
}
