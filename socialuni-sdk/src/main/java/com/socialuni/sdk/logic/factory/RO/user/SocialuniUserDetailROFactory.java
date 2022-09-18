package com.socialuni.sdk.logic.factory.RO.user;

import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserImgDO;
import com.socialuni.sdk.dao.utils.SocialuniUserExpandDOUtil;
import com.socialuni.sdk.dao.utils.content.SocialuniUserImgDOUtil;
import com.socialuni.sdk.logic.factory.UserImgROFactory;
import com.socialuni.sdk.model.RO.user.SocialuniUserDetailRO;
import com.socialuni.sdk.model.RO.user.SocialuniUserFollowDetailRO;
import com.socialuni.sdk.model.RO.user.SocialuniUserImgRO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SocialuniUserDetailROFactory {
    public static SocialuniUserDetailRO getUserDetailRO(SocialuniUserDO userDO, SocialuniUserDO mineUser) {
        //user基础信息
        SocialuniUserFollowDetailRO socialUserDetailFollowRO = SocialuniUserFollowDetailROFactory.newSocialFollowUserRO(userDO, mineUser);

        //user基础信息
        SocialuniUserDetailRO userDetailVO = new SocialuniUserDetailRO(socialUserDetailFollowRO);

        String schoolName = SocialuniUserExpandDOUtil.getUserSchoolNameAllowNull(userDO.getUnionId());

        userDetailVO.setSchoolName(schoolName);
        //用户图片
        List<SocialuniUserImgDO> imgDOS = SocialuniUserImgDOUtil.getUserImgsTop6(userDO.getUnionId());
        List<SocialuniUserImgRO> imgVOS = UserImgROFactory.userImgDOToVOS(imgDOS);
        userDetailVO.setImgs(imgVOS);

        return userDetailVO;
    }
}
