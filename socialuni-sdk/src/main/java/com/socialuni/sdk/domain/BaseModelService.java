package com.socialuni.sdk.domain;

import com.socialuni.sdk.repository.CommentRepository;
import com.socialuni.sdk.repository.MessageRepository;
import com.socialuni.sdk.repository.ReportRepository;
import com.socialuni.sdk.repository.UserImgRepository;
import com.socialuni.sdk.repository.community.TalkRepository;
import com.socialuni.sdk.utils.TalkRedis;
import com.socialuni.sdk.model.DO.ReportDO;
import com.socialuni.sdk.model.DO.base.BaseModelDO;
import com.socialuni.sdk.model.DO.comment.SocialCommentDO;
import com.socialuni.sdk.model.DO.message.MessageDO;
import com.socialuni.sdk.model.DO.talk.SocialTalkDO;
import com.socialuni.sdk.model.DO.user.SocialUserImgDO;
import com.socialuni.social.exception.SocialBusinessException;
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
        } else {
            throw new SocialBusinessException("错误的内容类型");
        }
    }

    public Optional<ReportDO> findReportByModel(BaseModelDO model) {
        if (model instanceof SocialTalkDO) {
            SocialTalkDO talkDO = (SocialTalkDO) model;
            return reportRepository.findFirstOneByTalkId(talkDO.getUnionId());
        } else if (model instanceof SocialCommentDO) {
            SocialCommentDO commentDO = (SocialCommentDO) model;
            return reportRepository.findFirstOneByCommentId(commentDO.getUnionId());
        } else if (model instanceof MessageDO) {
            MessageDO messageDO = (MessageDO) model;
            return reportRepository.findFirstOneByMessageId(messageDO.getId());
        } else if (model instanceof SocialUserImgDO) {
            SocialUserImgDO userImgDO = (SocialUserImgDO) model;
            return reportRepository.findFirstOneByUserImgId(userImgDO.getId());
        } else {
            throw new SocialBusinessException("错误的内容类型");
        }
    }
}
