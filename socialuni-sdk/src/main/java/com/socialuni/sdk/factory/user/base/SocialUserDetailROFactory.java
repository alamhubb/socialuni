package com.socialuni.sdk.factory.user.base;

import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.DO.user.SocialUserImgDO;
import com.socialuni.sdk.model.RO.user.SocialUserDetailRO;
import com.socialuni.sdk.model.RO.user.UserImgRO;
import com.socialuni.sdk.model.RO.user.base.SocialUserFollowDetailRO;
import com.socialuni.sdk.factory.UserImgROFactory;
import com.socialuni.sdk.utils.model.DO.UserImgDOUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SocialUserDetailROFactory {
    public static SocialUserDetailRO getUserDetailRO(SocialUserDO userDO, SocialUserDO mineUser) {
        //user基础信息
        SocialUserFollowDetailRO socialUserDetailFollowRO = SocialUserFollowDetailROFactory.newSocialFollowUserRO(userDO, mineUser);

        //user基础信息
        SocialUserDetailRO userDetailVO = new SocialUserDetailRO(socialUserDetailFollowRO);

        //用户图片
        List<SocialUserImgDO> imgDOS = UserImgDOUtils.getImgs(userDO.getUnionId());
        List<UserImgRO> imgVOS = UserImgROFactory.userImgDOToVOS(imgDOS);
        userDetailVO.setImgs(imgVOS);

        return userDetailVO;
    }
}
