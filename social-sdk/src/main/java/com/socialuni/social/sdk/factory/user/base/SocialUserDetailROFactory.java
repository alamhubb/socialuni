package com.socialuni.social.sdk.factory.user.base;

import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.entity.model.DO.user.UserImgDO;
import com.socialuni.social.model.model.RO.user.SocialUserDetailRO;
import com.socialuni.social.model.model.RO.user.UserImgRO;
import com.socialuni.social.model.model.RO.user.base.SocialUserFollowDetailRO;
import com.socialuni.social.sdk.factory.UserImgROFactory;
import com.socialuni.social.sdk.utils.model.DO.UserImgDOUtils;
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
