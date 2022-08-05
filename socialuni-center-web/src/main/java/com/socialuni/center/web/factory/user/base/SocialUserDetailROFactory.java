package com.socialuni.center.web.factory.user.base;

import com.socialuni.center.web.model.DO.user.UserDO;
import com.socialuni.center.web.model.DO.user.UserImgDO;
import com.socialuni.center.web.model.RO.user.SocialUserDetailRO;
import com.socialuni.center.web.model.RO.user.UserImgRO;
import com.socialuni.center.web.model.RO.user.base.SocialUserFollowDetailRO;
import com.socialuni.center.web.factory.UserImgROFactory;
import com.socialuni.center.web.utils.model.DO.UserImgDOUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SocialUserDetailROFactory {
    public static SocialUserDetailRO getUserDetailRO(UserDO userDO, UserDO mineUser) {
        //user基础信息
        SocialUserFollowDetailRO socialUserDetailFollowRO = SocialUserFollowDetailROFactory.newSocialFollowUserRO(userDO, mineUser);

        //user基础信息
        SocialUserDetailRO userDetailVO = new SocialUserDetailRO(socialUserDetailFollowRO);

        //用户图片
        List<UserImgDO> imgDOS = UserImgDOUtils.getImgs(userDO.getId());
        List<UserImgRO> imgVOS = UserImgROFactory.userImgDOToVOS(imgDOS);
        userDetailVO.setImgs(imgVOS);

        return userDetailVO;
    }
}
