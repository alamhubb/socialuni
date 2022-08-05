package com.socialuni.center.web.factory.RO.user;

import com.socialuni.center.web.model.RO.user.CenterUserDetailRO;
import com.socialuni.center.web.model.RO.user.CenterUserFollowDetailRO;
import com.socialuni.center.web.model.RO.user.CenterUserImgRO;
import com.socialuni.center.web.factory.user.base.SocialUserDetailROFactory;
import com.socialuni.center.web.model.DO.user.SocialUserDO;
import com.socialuni.center.web.model.RO.user.SocialUserDetailRO;
import com.socialuni.center.web.model.RO.user.UserImgRO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CenterUserDetailROFactory {

    public static CenterUserDetailRO getUserDetailRO(SocialUserDO detailUser, SocialUserDO mineUser) {
        //user基础信息
        SocialUserDetailRO socialUserRO = SocialUserDetailROFactory.getUserDetailRO(detailUser, mineUser);

        CenterUserDetailRO centerUserDetailRO = CenterUserDetailROFactory.getUserDetailRO(socialUserRO, mineUser);

        return centerUserDetailRO;
    }

    public static CenterUserDetailRO getUserDetailRO(SocialUserDetailRO socialUserRO, SocialUserDO mineUser) {
        //user基础信息
        CenterUserFollowDetailRO centerUserFollowDetailRO = CenterUserFollowDetailROFactory.getUserFollowDetailRO(socialUserRO, mineUser);

        CenterUserDetailRO centerUserDetailRO = new CenterUserDetailRO(centerUserFollowDetailRO);
        List<UserImgRO> imgVOS = socialUserRO.getImgs();

        List<CenterUserImgRO> imgROES = CenterUserImgROFactory.getImgs(imgVOS, mineUser);

        centerUserDetailRO.setImgs(imgROES);

        return centerUserDetailRO;
    }
}
