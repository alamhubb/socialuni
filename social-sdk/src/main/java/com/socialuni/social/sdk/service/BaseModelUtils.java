package com.socialuni.social.sdk.service;


import com.socialuni.entity.model.DO.message.MessageDO;
import com.socialuni.constant.ContentType;
import com.socialuni.social.sdk.exception.ResultException;
import com.socialuni.social.sdk.exception.SocialParamsException;
import com.socialuni.entity.model.DO.ReportDO;
import com.socialuni.entity.model.DO.base.BaseModelDO;
import com.socialuni.entity.model.DO.base.BaseModelParentDO;
import com.socialuni.entity.model.DO.comment.CommentDO;
import com.socialuni.entity.model.DO.talk.TalkDO;
import com.socialuni.entity.model.DO.user.UserImgDO;
import com.socialuni.social.sdk.repository.MessageRepository;
import com.socialuni.social.sdk.repository.UserImgRepository;
import com.socialuni.social.sdk.utils.CommentUtils;
import com.socialuni.social.sdk.utils.TalkUtils;
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
        String reportContentType = reportDO.getReportContentType();
        if (!ContentType.reportContentTypeTypes.contains(reportContentType)) {
            throw new ResultException("不存在的内容类型");
        }
        BaseModelDO modelDO;
        if (reportContentType.equals(ContentType.talk)) {
            modelDO = TalkUtils.get(reportDO.getTalkId());
        } else if (reportContentType.equals(ContentType.comment)) {
            modelDO = CommentUtils.get(reportDO.getCommentId());
        } else if (reportContentType.equals(ContentType.message)) {
            modelDO = messageRepository.findById(reportDO.getMessageId()).get();
        }else if (reportContentType.equals(ContentType.user)) {
            modelDO = messageRepository.findById(reportDO.getMessageId()).get();
        } else {
            throw new ResultException("不存在的内容类型");
        }
        //todo userImg未继承model的问题
         /*else if (reportContentType.equals(ContentType.userImg)) {
            modelDO = userImgRepository.findById(reportDO.getUserImgId()).get();
        }*/
        return modelDO;
    }

    public static <T extends BaseModelParentDO> void setBaseModel(T baseModelParentDO, BaseModelDO model) {
        if (model instanceof TalkDO) {
            TalkDO talkDO = BaseModelUtils.getModelByClass(model);
            baseModelParentDO.setTalkId(talkDO.getId());
        } else if (model instanceof CommentDO) {
            CommentDO commentDO = BaseModelUtils.getModelByClass(model);
            baseModelParentDO.setCommentId(commentDO.getId());
        } else if (model instanceof MessageDO) {
            MessageDO messageDO = BaseModelUtils.getModelByClass(model);
            baseModelParentDO.setMessageId(messageDO.getId());
        } else if (model instanceof UserImgDO) {
            UserImgDO userImgDO = BaseModelUtils.getModelByClass(model);
            baseModelParentDO.setUserImgId(userImgDO.getId());
        } else {
            throw new ResultException("错误的内容类型");
        }
    }

    public static ReportDO setBaseModelId(ReportDO reportDO, BaseModelDO baseModelDO) {
        switch (baseModelDO.getReportContentType()) {
            case ContentType.userImg:
                reportDO.setUserImgId(baseModelDO.getId());
                break;
            case ContentType.talk:
                reportDO.setTalkId(baseModelDO.getId());
                break;
            case ContentType.comment:
                reportDO.setCommentId(baseModelDO.getId());
                break;
            case ContentType.message:
                reportDO.setMessageId(baseModelDO.getId());
                break;
            default:
                throw new SocialParamsException("错误的内容类型");
        }
        return reportDO;
    }

    public static <T> T getModelByClass(BaseModelDO model) {
        return (T) model;
    }

    public static <T> T getModelByClass(BaseModelDO model, Class<T> childClass) {
        return (T) model;
    }
}
