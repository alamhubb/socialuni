package com.socialuni.sdk.logic.domain;

import com.socialuni.sdk.dao.DO.talk.SocialTalkImgDO;
import com.socialuni.sdk.dao.repository.CommentRepository;
import com.socialuni.sdk.dao.repository.MessageRepository;
import com.socialuni.sdk.dao.repository.ReportRepository;
import com.socialuni.sdk.dao.repository.UserImgRepository;
import com.socialuni.sdk.dao.repository.community.TalkImgRepository;
import com.socialuni.sdk.dao.repository.community.TalkRepository;
import com.socialuni.sdk.utils.TalkRedis;
import com.socialuni.sdk.dao.DO.ReportDO;
import com.socialuni.sdk.dao.DO.base.BaseModelDO;
import com.socialuni.sdk.dao.DO.comment.SocialCommentDO;
import com.socialuni.sdk.dao.DO.message.MessageDO;
import com.socialuni.sdk.dao.DO.talk.SocialTalkDO;
import com.socialuni.sdk.dao.DO.user.SocialUserImgDO;
import com.socialuni.social.web.sdk.exception.SocialBusinessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class BaseModelService {
    @Resource
    private TalkRepository talkRepository;
    @Resource
    private CommentRepository commentRepository;
    @Resource
    private UserImgRepository userImgRepository;
    @Resource
    private MessageRepository messageRepository;
    @Resource
    private ReportRepository reportRepository;
    @Resource
    private TalkRedis talkRedis;
    @Resource
    private TalkImgRepository talkImgRepository;

    public BaseModelDO save(BaseModelDO model) {
        if (model instanceof SocialTalkDO) {
            SocialTalkDO talkDO = (SocialTalkDO) model;
            return talkRedis.save(talkDO);
        } else if (model instanceof SocialCommentDO) {
            SocialCommentDO commentDO = (SocialCommentDO) model;
            return commentRepository.save(commentDO);
        } else if (model instanceof MessageDO) {
            MessageDO messageDO = (MessageDO) model;
            return messageRepository.save(messageDO);
        } else if (model instanceof SocialUserImgDO) {
            SocialUserImgDO userImgDO = (SocialUserImgDO) model;
            return userImgRepository.save(userImgDO);
        } else if (model instanceof SocialTalkImgDO) {
            SocialTalkImgDO talkImgDO = (SocialTalkImgDO) model;
            return talkImgRepository.save(talkImgDO);
        } else {
            throw new SocialBusinessException("错误的内容类型");
        }
    }
/*
    public Optional<ReportDO> findReportByModel(BaseModelDO model) {
        if (model instanceof SocialTalkDO) {
            SocialTalkDO talkDO = (SocialTalkDO) model;
            return reportRepository.findFirstOneByTalkId(talkDO.getUnionId());
        } else if (model instanceof SocialCommentDO) {
            SocialCommentDO commentDO = (SocialCommentDO) model;
            return reportRepository.findFirstOneByCommentId(commentDO.getUnionId());
        } else if (model instanceof MessageDO) {
            MessageDO messageDO = (MessageDO) model;
            return reportRepository.findFirstOneByMessageId(messageDO.getUnionId());
        } else if (model instanceof SocialUserImgDO) {
            SocialUserImgDO userImgDO = (SocialUserImgDO) model;
            return reportRepository.findFirstOneByUserImgId(userImgDO.getUnionId());
        } else {
            throw new SocialBusinessException("错误的内容类型");
        }
    }*/
}
