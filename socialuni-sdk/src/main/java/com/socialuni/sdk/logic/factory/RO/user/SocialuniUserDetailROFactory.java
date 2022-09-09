package com.socialuni.sdk.logic.factory.RO.user;

import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.dao.DO.user.SocialUserImgDO;
import com.socialuni.sdk.logic.factory.UserImgROFactory;
import com.socialuni.sdk.model.RO.user.SocialuniUserDetailRO;
import com.socialuni.sdk.model.RO.user.SocialuniUserFollowDetailRO;
import com.socialuni.sdk.model.RO.user.SocialuniUserImgRO;
import com.socialuni.sdk.utils.SocialuniUserExpandUtil;
import com.socialuni.sdk.utils.model.DO.UserImgDOUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SocialuniUserDetailROFactory {
    public static SocialuniUserDetailRO getUserDetailRO(SocialuniUserDO userDO, SocialuniUserDO mineUser) {
        //user基础信息
        SocialuniUserFollowDetailRO socialUserDetailFollowRO = SocialuniUserFollowDetailROFactory.newSocialFollowUserRO(userDO, mineUser);

        //user基础信息
        SocialuniUserDetailRO userDetailVO = new SocialuniUserDetailRO(socialUserDetailFollowRO);

        String schoolName = SocialuniUserExpandUtil.getUserSchoolName(userDO.getId());

        userDetailVO.setSchoolName(schoolName);
        //用户图片
        List<SocialUserImgDO> imgDOS = UserImgDOUtils.getImgs(userDO.getUnionId());
        List<SocialuniUserImgRO> imgVOS = UserImgROFactory.userImgDOToVOS(imgDOS);
        userDetailVO.setImgs(imgVOS);

        return userDetailVO;
    }
}
