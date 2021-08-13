package com.socialuni.sdk.factory.user.base;

import com.socialuni.sdk.dao.FollowDao;
import com.socialuni.entity.model.DO.user.UserDO;
import com.socialuni.entity.model.DO.user.UserImgDO;
import com.socialuni.sdk.utils.model.DO.UserImgDOUtils;
import com.socialuni.sdk.factory.UserImgROFactory;
import com.socialuni.social.model.model.RO.user.SocialUserDetailRO;
import com.socialuni.social.model.model.RO.user.UserImgRO;
import com.socialuni.social.model.model.RO.user.base.SocialUserFollowDetailRO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class SocialUserDetailROFactory {
    static FollowDao followDao;

    @Resource
    public void setFollowDao(FollowDao followDao) {
        SocialUserDetailROFactory.followDao = followDao;
    }


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
