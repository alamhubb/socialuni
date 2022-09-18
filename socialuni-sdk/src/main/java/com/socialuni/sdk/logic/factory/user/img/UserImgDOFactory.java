package com.socialuni.sdk.logic.factory.user.img;


import com.socialuni.sdk.logic.service.content.SocialuniContentCheckUtil;
import com.socialuni.sdk.utils.ImgContentUtil;
import com.socialuni.sdk.utils.UnionIdUtil;
import com.socialuni.sdk.constant.socialuni.ContentStatus;
import com.socialuni.sdk.constant.socialuni.SocialuniContentType;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.dao.DO.user.SocialUserImgDO;
import com.socialuni.sdk.constant.AppConfigConst;
import com.socialuni.sdk.model.QO.SocialuniImgAddQO;

import java.util.Date;

public class UserImgDOFactory {

    public static SocialUserImgDO toUserImgDO(SocialuniImgAddQO socialUserImgAddQO, SocialuniUserDO mineUser) {
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
        userImgDO.setContentType(SocialuniContentType.userImg);
        userImgDO.setReportNum(0);

        //包含未成年内容
        boolean hasUn18Content = SocialuniContentCheckUtil.hasUn18ContentThrowError(userImgDO.getContent());
        userImgDO.setHasUnderageContent(hasUn18Content);
        //是否成年认证通过
        userImgDO.setAdultAuth(false);
        //是否包含人物图片
        boolean hasPeople = ImgContentUtil.hasPeopleImg(userImgDO.getSrc());
        //如果用户包含人物头像,且用户未认证,
        if (hasPeople) {
            userImgDO.setHasPeopleImg(true);
            // todo && !imgDO.getAdultAuth() 目前认证不严格，所以暂时认证的也需要审核
//                if (!imgDO.getAdultAuth()) {
            //则添加一条待审核的动态，qq平台只查询审核通过的动态
//                }
        }

        Integer userImgUnionId = UnionIdUtil.createUserImgUnionId();
        userImgDO.setUnionId(userImgUnionId);
        return userImgDO;
    }
}
