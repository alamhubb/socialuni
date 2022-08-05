package com.socialuni.center.web.domain.comment;

import com.socialuni.center.web.constant.UserType;
import com.socialuni.center.web.domain.notify.NotifyDomain;
import com.socialuni.center.web.domain.report.ReportDomain;
import com.socialuni.center.web.factory.SocialCommentROFactory;
import com.socialuni.center.web.service.comment.CommentAddLineTransfer;
import com.socialuni.center.web.service.content.ModelContentCheck;
import com.socialuni.social.entity.model.DO.NotifyDO;
import com.socialuni.social.entity.model.DO.comment.CommentDO;
import com.socialuni.social.entity.model.DO.talk.TalkDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.exception.SocialParamsException;
import com.socialuni.social.model.model.QO.community.comment.SocialCommentPostQO;
import com.socialuni.social.model.model.RO.community.comment.SocialCommentRO;
import com.socialuni.social.constant.ContentStatus;
import com.socialuni.center.web.entity.comment.SocialPostCommentEntity;
import com.socialuni.center.web.repository.CommentRepository;
import com.socialuni.center.web.repository.community.TalkRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author qinkaiyuan
 * @date 2020-05-16 20:11
 */
@Service
@Slf4j
public class SocialCommentPostDomain {
    @Resource
    private NotifyDomain notifyDomain;
    @Resource
    private SocialPostCommentEntity socialPostCommentEntity;
    @Resource
    private ReportDomain reportDomain;
    @Resource
    private CommentRepository commentRepository;
    @Resource
    private TalkRepository talkRepository;
    @Resource
    private ModelContentCheck modelContentCheck;


    public SocialCommentRO postComment(UserDO mineUser, SocialCommentPostQO addQO) {
        //校验comment
        CommentAddLineTransfer commentAddLineTransfer = this.checkCommentAddVO(
                mineUser,
                addQO
        );

        //校验结果
        //校验时候，访问了数据库，存储了talk、parent、reply这些值，方便以后使用，传输使用
        //保存comment，内部关联保存了talk、parentComment、replyComment
        commentAddLineTransfer = socialPostCommentEntity.saveComment(addQO, commentAddLineTransfer, mineUser.getId());

        CommentDO commentDO = commentAddLineTransfer.getCommentDO();

        // 校验是否触发关键词
        reportDomain.checkKeywordsCreateReport(commentDO);

        //如果不为待审核，才发送通知
        if (commentDO.getStatus().equals(ContentStatus.enable)) {
            List<NotifyDO> notifyDOS = notifyDomain.saveCreateCommentNotifies(commentDO, commentAddLineTransfer.getTalk(), commentAddLineTransfer.getParentComment(), commentAddLineTransfer.getReplyComment(), mineUser);

            //推送消息
            notifyDomain.sendNotifies(notifyDOS, mineUser);
        }

        return SocialCommentROFactory.newTalkCommentRO(mineUser, commentDO, false);
    }


    //校验添加新增comment的评论是否正确
    private CommentAddLineTransfer checkCommentAddVO(UserDO requestUser, SocialCommentPostQO addVO) {
        //校验内容是否违规
        modelContentCheck.checkUserAndContent(addVO.getContent(), requestUser);

        Integer talkId = addVO.getTalkId();
        //因为与下面有关联所以拿到了上面
        Optional<TalkDO> talkOptional = talkRepository.findById(talkId);
        if (!talkOptional.isPresent()) {
            throw new SocialParamsException("无法评论不存在的动态");
        }
        TalkDO talkDO = talkOptional.get();
        //不为系统用户才校验
        if (!UserType.system.equals(requestUser.getType())) {
            if (!ObjectUtils.isEmpty(talkDO.getGlobalTop()) && talkDO.getGlobalTop() > 0) {
                throw new SocialParamsException("禁止评论官方置顶");
            }
        }

        CommentDO parentCommentDO = null;
        if (addVO.getCommentId() != null) {
            Optional<CommentDO> commentOptional = commentRepository.findById(addVO.getCommentId());
            if (!commentOptional.isPresent()) {
                throw new SocialParamsException("无法回复不存在的评论");
            }
            parentCommentDO = commentOptional.get();
        }
        //得到回复的评论
        CommentDO replyCommentDO = null;
        if (addVO.getReplyCommentId() != null) {
            Optional<CommentDO> replyCommentOptional = commentRepository.findById(addVO.getReplyCommentId());
            if (!replyCommentOptional.isPresent()) {
                throw new SocialParamsException("无法回复不存在的子评论");
            }
            replyCommentDO = replyCommentOptional.get();
        }
        return new CommentAddLineTransfer(talkDO, parentCommentDO, replyCommentDO);
    }
}
