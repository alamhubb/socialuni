package com.socialuni.admin.web.factory;

import com.socialuni.admin.web.model.ReportContentVO;
import com.socialuni.sdk.constant.socialuni.ContentType;
import com.socialuni.sdk.model.DO.comment.SocialCommentDO;
import com.socialuni.sdk.model.DO.tag.TagDO;
import com.socialuni.sdk.model.DO.talk.SocialTalkImgDO;
import com.socialuni.sdk.model.DO.talk.SocialTalkDO;
import com.socialuni.sdk.model.RO.community.tag.TagRO;
import com.socialuni.sdk.factory.SocialTalkImgROFactory;
import com.socialuni.sdk.factory.community.SocialTagROFactory;
import com.socialuni.sdk.store.SocialTagRedis;
import com.socialuni.sdk.utils.CommentUtils;
import com.socialuni.sdk.utils.TalkImgDOUtils;
import com.socialuni.sdk.utils.TalkUtils;
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
        if (reportContentType.equals(ContentType.talk)) {
            SocialTalkDO talkDO = TalkUtils.getNotNull(contentId);
            reportContentVO.setId(contentId);
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

            List<SocialTalkImgDO> imgDOS = TalkImgDOUtils.findTop3ByTalkId(contentId);

            reportContentVO.setImgs(SocialTalkImgROFactory.newTalkImgROS(imgDOS));
        } else if (reportContentType.equals(ContentType.comment)) {
            SocialCommentDO commentDO = CommentUtils.getNotNull(contentId);
            reportContentVO.setId(commentDO.getId());
            reportContentVO.setContent(commentDO.getContent());
            reportContentVO.setReportNum(commentDO.getReportNum());
            reportContentVO.setUserId(commentDO.getUserId());
        } else if (reportContentType.equals(ContentType.userImg)) {

            /*UserImgDO userImg = UserImgUtils.find(reportDO.getUserImgId());
            reportContentVO.userId = userImg.getUserId();
            reportContentVO.imgs = ImgVO.userImgDOToVOS(Collections.singletonList(userImg));
            reportContentVO.status = userImg.getStatus();

            reportContentVO.setId(userImg.getId());
            reportContentVO.setContent(commentDO.getContent());
            reportContentVO.setReportNum(commentDO.getReportNum());
            reportContentVO.setUserId(commentDO.getUserId());*/
        } else if (reportContentType.equals(ContentType.message)) {
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
