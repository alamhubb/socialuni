package com.socialuni.admin.web.factory;

import com.socialuni.admin.web.model.ReportContentVO;
import com.socialuni.sdk.constant.socialuni.SocialuniContentType;
import com.socialuni.sdk.dao.DO.community.comment.SocialuniCommentDO;
import com.socialuni.sdk.dao.DO.community.talk.SocialuniTalkDO;
import com.socialuni.sdk.dao.DO.community.talk.SocialuniTalkImgDO;
import com.socialuni.sdk.dao.DO.tag.TagDO;
import com.socialuni.sdk.dao.store.SocialTagRedis;
import com.socialuni.sdk.dao.utils.content.SocialuniCommentDOUtil;
import com.socialuni.sdk.dao.utils.content.SocialuniTalkDORedis;
import com.socialuni.sdk.dao.utils.content.SocialuniTalkDOUtil;
import com.socialuni.sdk.dao.utils.content.SocialuniTalkImgDOUtil;
import com.socialuni.sdk.logic.factory.SocialTalkImgROFactory;
import com.socialuni.sdk.logic.factory.community.SocialTagROFactory;
import com.socialuni.sdk.model.RO.community.tag.TagRO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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

//            SocialCommentROFactory.getTalkCommentROs()
//
//            CommentUtils.getAll(talkDO.getId()).stream().filter((SocialCommentDO commentDO) -> commentDO.getUserId().equals(userId)).map(talk::new).collect(Collectors.toList());
//            reportContentVO.setComments(talkDO.getUserId());

            List<TagDO> tagDOS = socialTagRedis.getTagsByTalkId(contentId);
            List<TagRO> tagROES = SocialTagROFactory.tagDOToROS(tagDOS);
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

            /*UserImgDO userImg = UserImgUtils.find(reportDO.getUserImgId());
            reportContentVO.userId = userImg.getUserId();
            reportContentVO.imgs = ImgVO.userImgDOToVOS(Collections.singletonList(userImg));
            reportContentVO.status = userImg.getStatus();

            reportContentVO.setId(userImg.getId());
            reportContentVO.setContent(commentDO.getContent());
            reportContentVO.setReportNum(commentDO.getReportNum());
            reportContentVO.setUserId(commentDO.getUserId());*/
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
