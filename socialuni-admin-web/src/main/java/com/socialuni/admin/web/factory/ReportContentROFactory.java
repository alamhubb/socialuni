package com.socialuni.admin.web.factory;

import com.socialuni.admin.web.model.ReportContentVO;
import com.socialuni.social.tance.sdk.enumeration.SocialuniContentType;
import com.socialuni.social.community.sdk.model.SocialuniCommentModel;
import com.socialuni.social.community.sdk.model.SocialuniTalkModel;
import com.socialuni.social.sdk.dao.DO.community.talk.SocialuniTalkImgModel;
import com.socialuni.social.community.sdk.model.TagModel;
import com.socialuni.social.sdk.dao.store.SocialTagRedis;
import com.socialuni.social.sdk.dao.utils.content.SocialuniCommentDOUtil;
import com.socialuni.social.sdk.dao.utils.content.SocialuniTalkDOUtil;
import com.socialuni.social.sdk.dao.utils.content.SocialuniTalkImgDOUtil;
import com.socialuni.social.sdk.logic.factory.SocialTalkImgROFactory;
import com.socialuni.social.sdk.logic.factory.community.SocialTagROFactory;
import com.socialuni.social.sdk.model.RO.community.tag.TagRO;
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
            SocialuniTalkModel talkDO = SocialuniTalkDOUtil.getTalkNotNull(contentId);
            reportContentVO.setId(contentId);
//            reportContentVO.setViolateType(talkDO.getViolateType());
            reportContentVO.setContent(talkDO.getContent());
            reportContentVO.setReportNum(talkDO.getReportNum());
            reportContentVO.setUserId(talkDO.getUserId());

//            SocialCommentROFactory.getTalkCommentROs()
//
//            CommentUtils.getAll(talkDO.getId()).stream().filter((SocialCommentDO commentDO) -> commentDO.getUserId().equals(userId)).map(talk::new).collect(Collectors.toList());
//            reportContentVO.setComments(talkDO.getUserId());

            List<?  extends TagModel> tagModels = socialTagRedis.getTagsByTalkId(contentId);
            List<TagRO> tagROES = SocialTagROFactory.tagDOToROS(tagModels);
            reportContentVO.setTags(tagROES);
            reportContentVO.setStatus(talkDO.getStatus());

            List<SocialuniTalkImgModel> imgDOS = SocialuniTalkImgDOUtil.getTalkImgsTop3(contentId);

            reportContentVO.setImgs(SocialTalkImgROFactory.newTalkImgROS(imgDOS));
        } else if (reportContentType.equals(SocialuniContentType.comment)) {
            SocialuniCommentModel commentDO = SocialuniCommentDOUtil.getNotCommentNull(contentId);
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
