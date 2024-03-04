package com.socialuni.social.sdk.dao.utils.content;


import com.socialuni.social.common.api.entity.SocialuniUnionContentBaseDO;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.community.sdk.dao.DO.SocialuniCommentDO;
import com.socialuni.social.community.sdk.dao.DO.SocialuniTalkDO;
import com.socialuni.social.community.sdk.dao.SocialuniCommentDOUtil;
import com.socialuni.social.community.sdk.dao.SocialuniTalkDORedis;
import com.socialuni.social.community.sdk.dao.SocialuniTalkDOUtil;
import com.socialuni.social.community.sdk.dao.SocialuniTalkImgDOUtil;
import com.socialuni.social.community.sdk.repository.CommentRepository;
import com.socialuni.social.sdk.dao.DO.base.BaseModelParentDO;
import com.socialuni.social.community.sdk.dao.DO.SocialuniTalkImgDO;
import com.socialuni.social.sdk.im.dao.DO.message.SocialuniMessageDO;
import com.socialuni.social.sdk.im.dao.repository.SocialuniMessageRepository;
import com.socialuni.social.community.sdk.repository.TalkImgRepository;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.tance.sdk.model.SocialuniUnionIdModler;
import com.socialuni.social.user.sdk.dao.DO.SocialuniUserImgDo;
import com.socialuni.social.user.sdk.repository.SocialuniUserImgRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialuniContentDOUtil<T> {
    private static SocialuniMessageRepository messageRepository;
    private static SocialuniUserImgRepository userImgRepository;
    private static CommentRepository commentApi;
    private static SocialuniTalkDORedis talkRedis;
    private static TalkImgRepository talkImgRepository;

    @Resource
    public void setMessageRepository(SocialuniMessageRepository messageRepository) {
        SocialuniContentDOUtil.messageRepository = messageRepository;
    }

    @Resource
    public void setUserImgRepository(SocialuniUserImgRepository userImgRepository) {
        SocialuniContentDOUtil.userImgRepository = userImgRepository;
    }

    @Resource
    public void setCommentRepository(CommentRepository commentApi) {
        SocialuniContentDOUtil.commentApi = commentApi;
    }

    @Resource
    public void setTalkRedis(SocialuniTalkDORedis talkRedis) {
        SocialuniContentDOUtil.talkRedis = talkRedis;
    }

    @Resource
    public void setTalkImgRepository(TalkImgRepository talkImgRepository) {
        SocialuniContentDOUtil.talkImgRepository = talkImgRepository;
    }

    public static SocialuniUnionContentBaseDO save(SocialuniUnionContentBaseDO model) {
        String contentType = model.getContentType();
        if (!SocialuniContentType.unionIdSupportTypes.contains(contentType)) {
            throw new SocialParamsException("错误的内容类型");
        }
        if (model instanceof SocialuniTalkDO) {
            SocialuniTalkDO talkDO = (SocialuniTalkDO) model;
            return talkRedis.save(talkDO);
        } else if (model instanceof SocialuniCommentDO) {
            SocialuniCommentDO commentDO = (SocialuniCommentDO) model;
            return commentApi.savePut(commentDO);
        } else if (model instanceof SocialuniMessageDO) {
            SocialuniMessageDO messageDO = (SocialuniMessageDO) model;
            return messageRepository.save(messageDO);
        } else if (model instanceof SocialuniUserImgDo) {
            SocialuniUserImgDo userImgDO = (SocialuniUserImgDo) model;
            return userImgRepository.savePut(userImgDO);
        } else if (model instanceof SocialuniTalkImgDO) {
            SocialuniTalkImgDO talkImgDO = (SocialuniTalkImgDO) model;
            return talkImgRepository.save(talkImgDO);
        } else {
            throw new SocialBusinessException("错误的内容类型");
        }
    }

    public static SocialuniUnionContentBaseDO getContentDOByContentId(Integer contentId) {
        SocialuniUnionIdModler socialuniUnionIdModler = SocialuniUnionIdFacede.getUnionDOByUnionIdNotNull(contentId);
        String contentType = socialuniUnionIdModler.getContentType();
        if (!SocialuniContentType.unionIdSupportTypes.contains(contentType)) {
            throw new SocialParamsException("错误的内容类型");
        }
        SocialuniUnionContentBaseDO contentBaseDO;
        if (contentType.equals(SocialuniContentType.user)) {
            contentBaseDO = SocialuniUserUtil.getAndCheckUserNotNull(contentId);
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

    public static <T extends BaseModelParentDO> void setBaseModel(T baseModelParentDO, SocialuniUnionContentBaseDO model) {
        if (model instanceof SocialuniTalkDO) {
            SocialuniTalkDO talkDO = SocialuniContentDOUtil.getModelByClass(model);
            baseModelParentDO.setTalkId(talkDO.getUnionId());
        } else if (model instanceof SocialuniCommentDO) {
            SocialuniCommentDO commentDO = SocialuniContentDOUtil.getModelByClass(model);
            baseModelParentDO.setCommentId(commentDO.getUnionId());
        } else if (model instanceof SocialuniMessageDO) {
            SocialuniMessageDO messageDO = SocialuniContentDOUtil.getModelByClass(model);
            baseModelParentDO.setMessageId(messageDO.getUnionId());
        } else if (model instanceof SocialuniUserImgDo) {
            SocialuniUserImgDo userImgDO = SocialuniContentDOUtil.getModelByClass(model);
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

    public static <T> T getModelByClass(SocialuniUnionContentBaseDO model) {
        return (T) model;
    }

    public static <T> T getModelByClass(SocialuniUnionContentBaseDO model, Class<T> childClass) {
        return (T) model;
    }
}
