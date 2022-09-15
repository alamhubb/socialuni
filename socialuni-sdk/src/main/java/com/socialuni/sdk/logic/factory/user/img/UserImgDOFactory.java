package com.socialuni.sdk.logic.factory.user.img;


import com.socialuni.sdk.config.SocialuniSystemConst;
import com.socialuni.sdk.utils.UnionIdDbUtil;
import com.socialuni.sdk.constant.socialuni.ContentStatus;
import com.socialuni.sdk.constant.socialuni.ContentType;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.dao.DO.user.SocialUserImgDO;
import com.socialuni.sdk.constant.AppConfigConst;
import com.socialuni.sdk.model.QO.user.SocialUserImgAddQO;

import java.util.Date;

public class UserImgDOFactory {

    public static SocialUserImgDO toUserImgDO(SocialUserImgAddQO socialUserImgAddQO, SocialuniUserDO mineUser) {
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
        Integer userImgUnionId = UnionIdDbUtil.createUserImgUnionId();
        userImgDO.setUnionId(userImgUnionId);

        mineUser.setAvatar(SocialuniSystemConst.getStaticResourceUrl() + userImgDO.getSrc() + "!avatar");

        return userImgDO;
    }
}
