package com.socialuni.sdk.logic.service;


import com.socialuni.sdk.dao.DO.ReportDO;
import com.socialuni.sdk.dao.DO.base.BaseModelDO;
import com.socialuni.sdk.dao.DO.base.BaseModelParentDO;
import com.socialuni.sdk.dao.DO.comment.SocialCommentDO;
import com.socialuni.sdk.dao.DO.message.MessageDO;
import com.socialuni.sdk.dao.DO.talk.SocialTalkDO;
import com.socialuni.sdk.dao.DO.user.SocialUserImgDO;
import com.socialuni.sdk.dao.repository.MessageRepository;
import com.socialuni.sdk.dao.repository.UserImgRepository;
import com.socialuni.sdk.utils.CommentUtils;
import com.socialuni.sdk.utils.TalkUtils;
import com.socialuni.sdk.constant.socialuni.SocialuniContentType;
import com.socialuni.social.web.sdk.exception.SocialBusinessException;
import com.socialuni.social.web.sdk.exception.SocialParamsException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class BaseModelUtils<T> {
    private static MessageRepository messageRepository;
    private static UserImgRepository userImgRepository;

    @Resource
    public void setMessageRepository(MessageRepository messageRepository) {
        BaseModelUtils.messageRepository = messageRepository;
    }

    @Resource
    public void setUserImgRepository(UserImgRepository userImgRepository) {
        BaseModelUtils.userImgRepository = userImgRepository;
    }

    public static BaseModelDO getModelByReport(ReportDO reportDO) {
        String reportContentType = reportDO.getContentType();
        if (!SocialuniContentType.reportContentTypeTypes.contains(reportContentType)) {
            throw new SocialBusinessException("不存在的内容类型");
        }
        BaseModelDO modelDO;
        if (reportContentType.equals(SocialuniContentType.talk)) {
            modelDO = TalkUtils.getNotNull(reportDO.getContentId());
        } else if (reportContentType.equals(SocialuniContentType.comment)) {
            modelDO = CommentUtils.getNotNull(reportDO.getContentId());
        } else if (reportContentType.equals(SocialuniContentType.message)) {
            modelDO = messageRepository.findById(reportDO.getContentId()).get();
        } else if (reportContentType.equals(SocialuniContentType.userImg)) {
            modelDO = userImgRepository.findOneByUnionId(reportDO.getContentId());
        } else {
            throw new SocialBusinessException("不存在的内容类型");
        }
        //todo userImg未继承model的问题
         /*else if (reportContentType.equals(ContentType.userImg)) {
            modelDO = userImgRepository.findById(reportDO.getUserImgId()).get();
        }*/
        return modelDO;
    }

    public static <T extends BaseModelParentDO> void setBaseModel(T baseModelParentDO, BaseModelDO model) {
        if (model instanceof SocialTalkDO) {
            SocialTalkDO talkDO = BaseModelUtils.getModelByClass(model);
            baseModelParentDO.setTalkId(talkDO.getUnionId());
        } else if (model instanceof SocialCommentDO) {
            SocialCommentDO commentDO = BaseModelUtils.getModelByClass(model);
            baseModelParentDO.setCommentId(commentDO.getUnionId());
        } else if (model instanceof MessageDO) {
            MessageDO messageDO = BaseModelUtils.getModelByClass(model);
            baseModelParentDO.setMessageId(messageDO.getUnionId());
        } else if (model instanceof SocialUserImgDO) {
            SocialUserImgDO userImgDO = BaseModelUtils.getModelByClass(model);
            baseModelParentDO.setUserImgId(userImgDO.getUnionId());
        } else {
            throw new SocialBusinessException("错误的内容类型");
        }
    }

   /* public static ReportDO setBaseModelId(ReportDO reportDO, BaseModelDO baseModelDO) {
        switch (baseModelDO.getReportContentType()) {
            case SocialuniContentType.userImg:
                reportDO.setUserImgId(baseModelDO.getUnionId());
                break;
            case SocialuniContentType.talk:
                reportDO.setTalkId(baseModelDO.getUnionId());
                break;
            case SocialuniContentType.comment:
                reportDO.setCommentId(baseModelDO.getUnionId());
                break;
            case SocialuniContentType.message:
                reportDO.setMessageId(baseModelDO.getUnionId());
                break;
            default:
                throw new SocialParamsException("错误的内容类型");
        }
        return reportDO;
    }*/

    public static <T> T getModelByClass(BaseModelDO model) {
        return (T) model;
    }

    public static <T> T getModelByClass(BaseModelDO model, Class<T> childClass) {
        return (T) model;
    }
}
