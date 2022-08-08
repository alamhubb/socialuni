package com.socialuni.center.web.factory.user.img;


import com.socialuni.social.constant.ContentStatus;
import com.socialuni.social.constant.ContentType;
import com.socialuni.center.web.model.DO.user.SocialUserDO;
import com.socialuni.center.web.model.DO.user.SocialUserImgDO;
import com.socialuni.center.web.constant.AppConfigConst;
import com.socialuni.center.web.model.QO.user.SocialUserImgAddQO;

import java.util.Date;

public class UserImgDOFactory {

    public static SocialUserImgDO toUserImgDO(SocialUserImgAddQO socialUserImgAddQO, SocialUserDO mineUser) {
        //这里需要记录，变更历史，通过照片有效无效记录，
        SocialUserImgDO userImgDO = new SocialUserImgDO();
        userImgDO.setSrc(socialUserImgAddQO.getSrc());
        userImgDO.setAspectRatio(socialUserImgAddQO.getAspectRatio());
        userImgDO.setQuality(socialUserImgAddQO.getQuality());
        userImgDO.setSize(socialUserImgAddQO.getSize());
        userImgDO.setUserId(mineUser.getUnionId());
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
