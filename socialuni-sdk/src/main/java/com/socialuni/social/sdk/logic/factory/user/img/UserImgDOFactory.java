package com.socialuni.social.sdk.logic.factory.user.img;


import com.socialuni.social.sdk.constant.socialuni.ContentStatus;
import com.socialuni.social.sdk.constant.socialuni.SocialuniContentType;
import com.socialuni.social.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.social.sdk.dao.DO.user.SocialuniUserImgDO;
import com.socialuni.social.sdk.logic.service.content.SocialuniTextContentUtil;
import com.socialuni.social.sdk.model.QO.SocialuniImgAddQO;
import com.socialuni.social.sdk.utils.SocialuniImgContentUtil;
import com.socialuni.social.sdk.utils.SocialuniUnionIdUtil;

import java.util.Date;

public class UserImgDOFactory {

    public static SocialuniUserImgDO toUserImgDO(SocialuniImgAddQO socialUserImgAddQO, SocialuniUserDO mineUser) {
        //这里需要记录，变更历史，通过照片有效无效记录，
        SocialuniUserImgDO userImgDO = new SocialuniUserImgDO();

        Integer userImgUnionId = SocialuniUnionIdUtil.createUserImgUnionId();
        userImgDO.setUnionId(userImgUnionId);

        userImgDO.setSrc(socialUserImgAddQO.getSrc());
        userImgDO.setAspectRatio(socialUserImgAddQO.getAspectRatio());
        userImgDO.setQuality(socialUserImgAddQO.getQuality());
        userImgDO.setContent(socialUserImgAddQO.getContent());
        userImgDO.setSize(socialUserImgAddQO.getSize());
        userImgDO.setUserId(mineUser.getUnionId());
        userImgDO.setStatus(ContentStatus.enable);
        userImgDO.setCreateTime(new Date());
        userImgDO.setContentType(SocialuniContentType.userImg);
        userImgDO.setReportNum(0);
        SocialuniTextContentUtil.setHasUnderageAndContactInfoByContentText(userImgDO.getContent(), userImgDO);
        //是否成年认证通过
        userImgDO.setPeopleImgIsAdult(false);
        //是否包含人物图片
        boolean hasPeople = SocialuniImgContentUtil.hasPeopleImg(userImgDO.getSrc());
        //如果用户包含人物头像,且用户未认证,
        if (hasPeople) {
            userImgDO.setHasPeopleImg(true);
            // todo && !imgDO.getAdultAuth() 目前认证不严格，所以暂时认证的也需要审核
//                if (!imgDO.getAdultAuth()) {
            //则添加一条待审核的动态，qq平台只查询审核通过的动态
//                }
        }
        return userImgDO;
    }
}