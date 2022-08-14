package com.socialuni.sdk.domain.comment;

import com.socialuni.sdk.constant.SocialuniCommonConst;
import com.socialuni.sdk.constant.UserType;
import com.socialuni.sdk.domain.notify.NotifyDomain;
import com.socialuni.sdk.domain.report.ReportDomain;
import com.socialuni.sdk.entity.comment.SocialPostCommentEntity;
import com.socialuni.sdk.factory.SocialCommentROFactory;
import com.socialuni.sdk.model.DO.comment.SocialCommentDO;
import com.socialuni.sdk.model.DO.tag.TagDO;
import com.socialuni.sdk.model.DO.talk.SocialTalkDO;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.QO.community.comment.SocialCommentPostQO;
import com.socialuni.sdk.model.RO.community.comment.SocialCommentRO;
import com.socialuni.sdk.repository.CommentRepository;
import com.socialuni.sdk.repository.community.TalkRepository;
import com.socialuni.sdk.service.comment.CommentAddLineTransfer;
import com.socialuni.sdk.service.content.ModelContentCheck;
import com.socialuni.sdk.store.SocialTagRedis;
import com.socialuni.social.exception.SocialParamsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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
    @Resource
    private  SocialTagRedis socialTagRedis;

    @Transactional
    public SocialCommentRO postComment(SocialUserDO mineUser, SocialCommentPostQO addQO) {
        List<TagDO> tagDOS = socialTagRedis.getTagsByTalkId(addQO.getTalkId());
        List<String> tagNames = tagDOS.stream().map(TagDO::getName).collect(Collectors.toList());
        //开发环境不校验
        if (!tagNames.contains(SocialuniCommonConst.devEnvTagName)) {
            //校验内容是否违规
            modelContentCheck.checkUserAndContent(addQO.getContent(), mineUser);
        }
        //校验结果
        //校验时候，访问了数据库，存储了talk、parent、reply这些值，方便以后使用，传输使用
        //保存comment，内部关联保存了talk、parentComment、replyComment

        SocialCommentDO commentDO = socialPostCommentEntity.saveComment(addQO, mineUser.getUnionId());

        // 校验是否触发关键词
        reportDomain.checkKeywordsCreateReport(commentDO);

        //如果不为待审核，才发送通知
        //注释通知相关功能
        /*if (commentDO.getStatus().equals(ContentStatus.enable)) {
            List<NotifyDO> notifyDOS = notifyDomain.saveCreateCommentNotifies(commentDO, commentAddLineTransfer.getTalk(), commentAddLineTransfer.getParentComment(), commentAddLineTransfer.getReplyComment(), mineUser);

            //推送消息
            notifyDomain.sendNotifies(notifyDOS, mineUser);
        }*/

        return SocialCommentROFactory.newTalkCommentRO(mineUser, commentDO, false);
    }


    //校验添加新增comment的评论是否正确
    private CommentAddLineTransfer checkCommentAddVO(SocialUserDO requestUser, SocialCommentPostQO addVO) {
        //校验内容是否违规
        modelContentCheck.checkUserAndContent(addVO.getContent(), requestUser);

        Integer talkId = addVO.getTalkId();
        //因为与下面有关联所以拿到了上面
        SocialTalkDO talkDO = talkRepository.findOneByUnionId(talkId);
        if (talkDO == null) {
            throw new SocialParamsException("无法评论不存在的动态");
        }
        //不为系统用户才校验
        if (!UserType.system.equals(requestUser.getType())) {
            if (!ObjectUtils.isEmpty(talkDO.getGlobalTop()) && talkDO.getGlobalTop() > 0) {
                throw new SocialParamsException("禁止评论官方置顶");
            }
        }

        SocialCommentDO parentCommentDO = null;
        if (addVO.getCommentId() != null) {
            parentCommentDO = commentRepository.findOneByUnionId(addVO.getCommentId());
            if (parentCommentDO == null) {
                throw new SocialParamsException("无法回复不存在的评论");
            }
        }
        //得到回复的评论
        SocialCommentDO replyCommentDO = null;
        if (addVO.getReplyCommentId() != null) {
            replyCommentDO = commentRepository.findOneByUnionId(addVO.getReplyCommentId());
            if (replyCommentDO == null) {
                throw new SocialParamsException("无法回复不存在的子评论");
            }
        }
        return new CommentAddLineTransfer(talkDO, parentCommentDO, replyCommentDO);
    }
}
