package com.socialuni.social.user.sdk.factory;


import com.socialuni.social.common.api.enumeration.ContentStatus;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.report.sdk.utils.SocialuniTextContentUtil;
import com.socialuni.social.common.sdk.model.SocialuniImgAddQO;
import com.socialuni.social.report.sdk.utils.SocialuniImgContentUtil;
import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.dao.DO.SocialuniUserImgDo;

import java.util.Date;

public class UserImgDOFactory {

    public static SocialuniUserImgDo toUserImgDO(SocialuniImgAddQO socialUserImgAddQO, SocialuniUserDo mineUser) {
        //这里需要记录，变更历史，通过照片有效无效记录，
        SocialuniUserImgDo userImgDO = new SocialuniUserImgDo();

        Integer userImgUnionId = SocialuniUnionIdFacede.createUserImgUnionId();
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
