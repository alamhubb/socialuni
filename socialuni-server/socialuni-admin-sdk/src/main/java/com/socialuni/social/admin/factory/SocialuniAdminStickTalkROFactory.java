package com.socialuni.social.admin.factory;

import com.socialuni.social.admin.model.SocialuniAdminStickTalkRO;
import com.socialuni.social.community.sdk.dao.DO.SocialuniTalkDO;
import com.socialuni.social.community.sdk.dao.DO.SocialuniTalkImgDO;
import com.socialuni.social.community.sdk.dao.SocialuniTalkDOUtil;
import com.socialuni.social.community.sdk.dao.SocialuniTalkImgDOUtil;
import com.socialuni.social.community.sdk.logic.factory.SocialTalkImgROFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SocialuniAdminStickTalkROFactory {

    public static SocialuniAdminStickTalkRO getTalkRO(Long contentId) {
        SocialuniAdminStickTalkRO reportContentVO = new SocialuniAdminStickTalkRO();
        SocialuniTalkDO talkDO = SocialuniTalkDOUtil.getTalkNotNull(contentId);
        reportContentVO.setId(contentId);
//            reportContentVO.setViolateType(talkDO.getViolateType());
        reportContentVO.setContent(talkDO.getContent());
        reportContentVO.setGlobalTop(talkDO.getGlobalTop());
        reportContentVO.setReportNum(talkDO.getReportNum());
        reportContentVO.setUserId(talkDO.getUserId());
        reportContentVO.setCreateTime(talkDO.getCreateTime());

//            SocialCommentROFactory.getTalkCommentROs()
//
//            CommentUtils.getAll(talkDO.getId()).stream().filter((SocialCommentDO commentDO) -> commentDO.getUserId().equals(userId)).map(talk::new).collect(Collectors.toList());
//            reportContentVO.setComments(talkDO.getUserId());

        reportContentVO.setStatus(talkDO.getStatus());

        List<SocialuniTalkImgDO> imgDOS = SocialuniTalkImgDOUtil.getTalkImgsTop3(contentId);
        reportContentVO.setImgs(SocialTalkImgROFactory.newTalkImgROS(imgDOS));
        return reportContentVO;
    }
}
