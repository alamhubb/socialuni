package com.socialuni.sdk.logic.factory.RO.user;

import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserExpandDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserImgDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserSocialCoinDO;
import com.socialuni.sdk.dao.utils.user.SocialuniUserExpandDOUtil;
import com.socialuni.sdk.dao.utils.content.SocialuniUserImgDOUtil;
import com.socialuni.sdk.dao.utils.user.SocialuniUserSocialCoinDOUtil;
import com.socialuni.sdk.logic.factory.UserImgROFactory;
import com.socialuni.sdk.model.RO.user.SocialuniUserDetailRO;
import com.socialuni.sdk.model.RO.user.SocialuniUserFollowDetailRO;
import com.socialuni.sdk.model.RO.user.SocialuniUserImgRO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SocialuniUserDetailROFactory {
    public static SocialuniUserDetailRO getUserDetailRO(SocialuniUserDO userDO, SocialuniUserDO mineUser) {
        //user基础信息
        SocialuniUserFollowDetailRO socialUserDetailFollowRO = SocialuniUserFollowDetailROFactory.newSocialFollowUserRO(userDO, mineUser);

        //user基础信息
        SocialuniUserDetailRO userDetailVO = new SocialuniUserDetailRO(socialUserDetailFollowRO);

        Integer userUnionId = userDO.getUnionId();

        SocialuniUserExpandDO socialuniUserExpandDO = SocialuniUserExpandDOUtil.getOrCreate(userUnionId);
        userDetailVO.setSchoolName(socialuniUserExpandDO.getSchoolName());

        String contactInfo = socialuniUserExpandDO.getContactInfo();
        if (StringUtils.isNotEmpty(contactInfo)) {
            //不为自己且开启了获取，则模糊化
            if (!userDetailVO.getIsMine() && socialuniUserExpandDO.getOpenContactInfo()) {
                contactInfo = contactInfo.charAt(0) + "*****";
            }
            userDetailVO.setContactInfo(contactInfo);
            userDetailVO.setOpenContactInfo(socialuniUserExpandDO.getOpenContactInfo());
        }
        SocialuniUserSocialCoinDO socialuniUserSocialCoinDO = SocialuniUserSocialCoinDOUtil.getOrCreate(userUnionId);
        userDetailVO.setSocialCoin(socialuniUserSocialCoinDO.getSocialCoin());
        //用户图片
        List<SocialuniUserImgDO> imgDOS = SocialuniUserImgDOUtil.getUserImgsTop6(userDO.getUnionId());
        List<SocialuniUserImgRO> imgVOS = UserImgROFactory.userImgDOToVOS(imgDOS);
        userDetailVO.setImgs(imgVOS);

        return userDetailVO;
    }
}
