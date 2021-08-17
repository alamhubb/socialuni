package com.socialuni.social.sdk.factory.user.img;


import com.socialuni.social.constant.ContentStatus;
import com.socialuni.social.constant.ContentType;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.entity.model.DO.user.UserImgDO;
import com.socialuni.social.sdk.constant.AppConfigConst;
import com.socialuni.social.sdk.model.QO.user.SocialUserImgAddQO;

import java.util.Date;

public class UserImgDOFactory {

    public static UserImgDO toUserImgDO(SocialUserImgAddQO socialUserImgAddQO, UserDO mineUser) {
        //这里需要记录，变更历史，通过照片有效无效记录，
        UserImgDO userImgDO = new UserImgDO();
        userImgDO.setSrc(socialUserImgAddQO.getSrc());
        userImgDO.setAspectRatio(socialUserImgAddQO.getAspectRatio());
        userImgDO.setQuality(socialUserImgAddQO.getQuality());
        userImgDO.setSize(socialUserImgAddQO.getSize());
        userImgDO.setUserId(mineUser.getId());
        userImgDO.setStatus(ContentStatus.enable);
        userImgDO.setCreateTime(new Date());
        userImgDO.setContent(AppConfigConst.img_content);
        userImgDO.setReportContentType(ContentType.userImg);
        userImgDO.setReportNum(0);
        userImgDO.setIsSelfAuth(false);
        mineUser.setAvatar(userImgDO.getSrc() + "!avatar");
        return userImgDO;
    }
}
