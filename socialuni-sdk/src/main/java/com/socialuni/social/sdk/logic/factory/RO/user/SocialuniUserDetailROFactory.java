package com.socialuni.social.sdk.logic.factory.RO.user;

import com.socialuni.social.user.sdk.model.SocialuniUserModel;
import com.socialuni.social.user.sdk.model.SocialuniUserExpandModel;
import com.socialuni.social.user.sdk.model.SocialuniUserImgModel;
import com.socialuni.social.user.sdk.model.SocialuniUserSocialCoinModel;
import com.socialuni.social.sdk.dao.utils.user.SocialuniUserExpandDOUtil;
import com.socialuni.social.sdk.dao.utils.content.SocialuniUserImgDOUtil;
import com.socialuni.social.sdk.dao.utils.user.SocialuniUserSocialCoinDOUtil;
import com.socialuni.social.sdk.logic.factory.UserImgROFactory;
import com.socialuni.social.sdk.model.RO.user.SocialuniUserDetailRO;
import com.socialuni.social.sdk.model.RO.user.SocialuniUserFollowDetailRO;
import com.socialuni.social.sdk.model.RO.user.SocialuniUserImgRO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SocialuniUserDetailROFactory {
    public static SocialuniUserDetailRO getUserDetailRO(SocialuniUserModel userDO, SocialuniUserModel mineUser) {
        //user基础信息
        SocialuniUserFollowDetailRO socialUserDetailFollowRO = SocialuniUserFollowDetailROFactory.newSocialFollowUserRO(userDO, mineUser);

        //user基础信息
        SocialuniUserDetailRO userDetailVO = new SocialuniUserDetailRO(socialUserDetailFollowRO);

        Integer userUnionId = userDO.getUnionId();

        SocialuniUserExpandModel socialuniUserExpandModel = SocialuniUserExpandDOUtil.getOrCreate(userUnionId);
        userDetailVO.setSchoolName(socialuniUserExpandModel.getSchoolName());

        String contactInfo = socialuniUserExpandModel.getContactInfo();
        if (StringUtils.isNotEmpty(contactInfo)) {
            //不为自己且开启了获取，则模糊化
            if (!userDetailVO.getIsMine() && socialuniUserExpandModel.getOpenContactInfo()) {
                contactInfo = contactInfo.charAt(0) + "*****";
            }
            userDetailVO.setContactInfo(contactInfo);
            userDetailVO.setOpenContactInfo(socialuniUserExpandModel.getOpenContactInfo());
        }
        SocialuniUserSocialCoinModel socialuniUserSocialCoinModel = SocialuniUserSocialCoinDOUtil.getOrCreate(userUnionId);
        userDetailVO.setSocialCoin(socialuniUserSocialCoinModel.getSocialCoin());
        //用户图片
        List<SocialuniUserImgModel> imgDOS = SocialuniUserImgDOUtil.getUserImgsTop6(userDO.getUnionId());
        List<SocialuniUserImgRO> imgVOS = UserImgROFactory.userImgDOToVOS(imgDOS);
        userDetailVO.setImgs(imgVOS);

        return userDetailVO;
    }
}
