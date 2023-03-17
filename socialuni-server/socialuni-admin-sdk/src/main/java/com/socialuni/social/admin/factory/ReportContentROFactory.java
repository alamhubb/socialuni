package com.socialuni.social.admin.factory;

import com.socialuni.social.admin.model.ReportContentVO;
import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.common.api.entity.SocialuniImgBaseDo;
import com.socialuni.social.community.sdk.entity.SocialuniCommentDO;
import com.socialuni.social.community.sdk.entity.SocialuniTalkDO;
import com.socialuni.social.community.sdk.entity.TagDO;
import com.socialuni.social.sdk.dao.DO.community.talk.SocialuniTalkImgDO;
import com.socialuni.social.sdk.dao.store.SocialTagRedis;
import com.socialuni.social.sdk.dao.utils.content.SocialuniCommentDOUtil;
import com.socialuni.social.sdk.dao.utils.content.SocialuniTalkDOUtil;
import com.socialuni.social.sdk.dao.utils.content.SocialuniTalkImgDOUtil;
import com.socialuni.social.sdk.dao.utils.content.SocialuniUserImgDOUtil;
import com.socialuni.social.sdk.logic.factory.SocialTalkImgROFactory;
import com.socialuni.social.sdk.logic.factory.community.SocialTagROFactory;
import com.socialuni.social.sdk.model.RO.community.tag.TagRO;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserImgDo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Component
public class ReportContentROFactory {
    private static SocialTagRedis socialTagRedis;

    @Resource
    public void setSocialTagRedis(SocialTagRedis socialTagRedis) {
        ReportContentROFactory.socialTagRedis = socialTagRedis;
    }

    public static ReportContentVO getReportContentVO(String reportContentType, Integer contentId) {
        ReportContentVO reportContentVO = new ReportContentVO();
        if (reportContentType.equals(SocialuniContentType.talk)) {
            SocialuniTalkDO talkDO = SocialuniTalkDOUtil.getTalkNotNull(contentId);
            reportContentVO.setId(contentId);
//            reportContentVO.setViolateType(talkDO.getViolateType());
            reportContentVO.setContent(talkDO.getContent());
            reportContentVO.setReportNum(talkDO.getReportNum());
            reportContentVO.setUserId(talkDO.getUserId());
            reportContentVO.setCreateTime(talkDO.getCreateTime());

//            SocialCommentROFactory.getTalkCommentROs()
//
//            CommentUtils.getAll(talkDO.getId()).stream().filter((SocialCommentDO commentDO) -> commentDO.getUserId().equals(userId)).map(talk::new).collect(Collectors.toList());
//            reportContentVO.setComments(talkDO.getUserId());

            List<?  extends TagDO> TagDOs = socialTagRedis.getTagsByTalkId(contentId);
            List<TagRO> tagROES = SocialTagROFactory.tagDOToROS(TagDOs);
            reportContentVO.setTags(tagROES);
            reportContentVO.setStatus(talkDO.getStatus());

            List<SocialuniTalkImgDO> imgDOS = SocialuniTalkImgDOUtil.getTalkImgsTop3(contentId);

            reportContentVO.setImgs(SocialTalkImgROFactory.newTalkImgROS(imgDOS));
        } else if (reportContentType.equals(SocialuniContentType.comment)) {
            SocialuniCommentDO commentDO = SocialuniCommentDOUtil.getNotCommentNull(contentId);
            reportContentVO.setId(commentDO.getUnionId());
            reportContentVO.setContent(commentDO.getContent());
//            reportContentVO.setViolateType(commentDO.getViolateType());
            reportContentVO.setReportNum(commentDO.getReportNum());
            reportContentVO.setUserId(commentDO.getUserId());
        } else if (reportContentType.equals(SocialuniContentType.userImg)) {
            SocialuniUserImgDo userImg =SocialuniUserImgDOUtil.getUserImgNotNull(contentId);
            reportContentVO.setId(userImg.getUnionId());
            reportContentVO.setContent(userImg.getContent());
            reportContentVO.setImgs(Collections.singletonList(SocialTalkImgROFactory.newTalkImgRO(userImg)));
            reportContentVO.setStatus(userImg.getStatus());
            reportContentVO.setReportNum(userImg.getReportNum());
            reportContentVO.setCreateTime(userImg.getCreateTime());

        } else if (reportContentType.equals(SocialuniContentType.message)) {
            /*MessageDO message = MessageStoreUtils.findById(reportDO.getMessageId());
            reportContentVO.userId = message.getUserId();
            reportContentVO.content = message.getContent();
            reportContentVO.status = message.getStatus();*/
        } else {
//            QingLogger.logger.error("未知的举报类型");
        }
        return reportContentVO;
    }
}
