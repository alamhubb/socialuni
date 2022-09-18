package com.socialuni.sdk.dao.utils.content;


import com.socialuni.sdk.constant.socialuni.SocialuniContentType;
import com.socialuni.sdk.dao.DO.SocialuniUnionIdDO;
import com.socialuni.sdk.dao.DO.base.BaseModelParentDO;
import com.socialuni.sdk.dao.DO.community.comment.SocialuniCommentDO;
import com.socialuni.sdk.dao.DO.community.talk.SocialuniTalkDO;
import com.socialuni.sdk.dao.DO.community.talk.SocialuniTalkImgDO;
import com.socialuni.sdk.dao.DO.message.MessageDO;
import com.socialuni.sdk.dao.DO.user.SocialUnionContentBaseDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserImgDO;
import com.socialuni.sdk.dao.repository.CommentRepository;
import com.socialuni.sdk.dao.repository.MessageRepository;
import com.socialuni.sdk.dao.repository.SocialuniUserImgRepository;
import com.socialuni.sdk.dao.repository.community.TalkImgRepository;
import com.socialuni.sdk.utils.SocialuniUnionIdUtil;
import com.socialuni.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.web.sdk.exception.SocialBusinessException;
import com.socialuni.social.web.sdk.exception.SocialParamsException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialuniContentDOUtil<T> {
    private static MessageRepository messageRepository;
    private static SocialuniUserImgRepository userImgRepository;
    private static CommentRepository commentRepository;
    private static SocialuniTalkDORedis talkRedis;
    private static TalkImgRepository talkImgRepository;

    @Resource
    public void setMessageRepository(MessageRepository messageRepository) {
        SocialuniContentDOUtil.messageRepository = messageRepository;
    }

    @Resource
    public void setUserImgRepository(SocialuniUserImgRepository userImgRepository) {
        SocialuniContentDOUtil.userImgRepository = userImgRepository;
    }

    @Resource
    public void setCommentRepository(CommentRepository commentRepository) {
        SocialuniContentDOUtil.commentRepository = commentRepository;
    }

    @Resource
    public void setTalkRedis(SocialuniTalkDORedis talkRedis) {
        SocialuniContentDOUtil.talkRedis = talkRedis;
    }

    @Resource
    public void setTalkImgRepository(TalkImgRepository talkImgRepository) {
        SocialuniContentDOUtil.talkImgRepository = talkImgRepository;
    }

    public static SocialUnionContentBaseDO save(SocialUnionContentBaseDO model) {
        String contentType = model.getContentType();
        if (!SocialuniContentType.unionIdSupportTypes.contains(contentType)) {
            throw new SocialParamsException("错误的内容类型");
        }
        if (model instanceof SocialuniTalkDO) {
            SocialuniTalkDO talkDO = (SocialuniTalkDO) model;
            return talkRedis.save(talkDO);
        } else if (model instanceof SocialuniCommentDO) {
            SocialuniCommentDO commentDO = (SocialuniCommentDO) model;
            return commentRepository.save(commentDO);
        } else if (model instanceof MessageDO) {
            MessageDO messageDO = (MessageDO) model;
            return messageRepository.save(messageDO);
        } else if (model instanceof SocialuniUserImgDO) {
            SocialuniUserImgDO userImgDO = (SocialuniUserImgDO) model;
            return userImgRepository.save(userImgDO);
        } else if (model instanceof SocialuniTalkImgDO) {
            SocialuniTalkImgDO talkImgDO = (SocialuniTalkImgDO) model;
            return talkImgRepository.save(talkImgDO);
        } else {
            throw new SocialBusinessException("错误的内容类型");
        }
    }

    public static SocialUnionContentBaseDO getContentDOByContentId(Integer contentId) {
        SocialuniUnionIdDO socialuniUnionIdDO = SocialuniUnionIdUtil.getUnionDOByUnionIdNotNull(contentId);
        String contentType = socialuniUnionIdDO.getContentType();
        if (!SocialuniContentType.unionIdSupportTypes.contains(contentType)) {
            throw new SocialParamsException("错误的内容类型");
        }
        SocialUnionContentBaseDO contentBaseDO = null;
        if (contentType.equals(SocialuniContentType.user)) {
            contentBaseDO = SocialuniUserUtil.getUserNotNull(contentId);
        } else if (contentType.equals(SocialuniContentType.userImg)) {
            contentBaseDO = SocialuniUserImgDOUtil.getUserImgNotNull(contentId);
        } else if (contentType.equals(SocialuniContentType.talk)) {
            contentBaseDO = SocialuniTalkDOUtil.getTalkNotNull(contentId);
        } else if (contentType.equals(SocialuniContentType.talkImg)) {
            contentBaseDO = SocialuniTalkImgDOUtil.getTalkImgNotNull(contentId);
        } else if (contentType.equals(SocialuniContentType.comment)) {
            contentBaseDO = SocialuniCommentDOUtil.getNotCommentNull(contentId);
        } else {
            throw new SocialBusinessException("错误的内容类型");
        }
        return contentBaseDO;
    }

    public static <T extends BaseModelParentDO> void setBaseModel(T baseModelParentDO, SocialUnionContentBaseDO model) {
        if (model instanceof SocialuniTalkDO) {
            SocialuniTalkDO talkDO = SocialuniContentDOUtil.getModelByClass(model);
            baseModelParentDO.setTalkId(talkDO.getUnionId());
        } else if (model instanceof SocialuniCommentDO) {
            SocialuniCommentDO commentDO = SocialuniContentDOUtil.getModelByClass(model);
            baseModelParentDO.setCommentId(commentDO.getUnionId());
        } else if (model instanceof MessageDO) {
            MessageDO messageDO = SocialuniContentDOUtil.getModelByClass(model);
            baseModelParentDO.setMessageId(messageDO.getUnionId());
        } else if (model instanceof SocialuniUserImgDO) {
            SocialuniUserImgDO userImgDO = SocialuniContentDOUtil.getModelByClass(model);
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

    public static <T> T getModelByClass(SocialUnionContentBaseDO model) {
        return (T) model;
    }

    public static <T> T getModelByClass(SocialUnionContentBaseDO model, Class<T> childClass) {
        return (T) model;
    }
}
