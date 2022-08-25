package com.socialuni.sdk.factory;

import com.socialuni.sdk.factory.user.base.SocialUserROFactory;
import com.socialuni.sdk.model.RO.community.comment.SocialCommentRO;
import com.socialuni.sdk.dao.CommentDao;
import com.socialuni.sdk.model.DO.comment.SocialCommentDO;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.repository.CommentRepository;
import com.socialuni.sdk.utils.SocialUserUtil;
import com.socialuni.sdk.utils.SystemUtil;
import com.socialuni.sdk.constant.socialuni.ContentStatus;
import com.socialuni.sdk.model.RO.user.base.SocialUserRO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class SocialCommentROFactory {
    private static CommentRepository commentRepository;
    private static CommentDao commentDao;

    @Resource
    public void setCommentDao(CommentDao commentDao) {
        SocialCommentROFactory.commentDao = commentDao;
    }

    @Resource
    public void setCommentRepository(CommentRepository commentRepository) {
        SocialCommentROFactory.commentRepository = commentRepository;
    }

    public static SocialCommentRO newTalkCommentRO(SocialUserDO mineUser, SocialCommentDO comment, boolean showAll) {
        SocialCommentRO socialCommentRO = new SocialCommentRO();
        socialCommentRO.setId(comment.getUnionId());
        socialCommentRO.setNo(comment.getNo());

        SocialUserDO commentUser = SocialUserUtil.getUserNotNull(comment.getUserId());
        SocialUserRO commentUserRO = SocialUserROFactory.getUserRO(commentUser);
        socialCommentRO.setUser(commentUserRO);

        socialCommentRO.setContent(comment.getContent());
//        socialCommentRO.setContentType(comment.getContentType());
        socialCommentRO.setHugNum(comment.getHugNum());
        socialCommentRO.setReportNum(comment.getReportNum());
        socialCommentRO.setCreateTime(comment.getCreateTime());
        socialCommentRO.setChildCommentNum(comment.getChildCommentNum());

        List<SocialCommentRO> socialCommentROS = SocialCommentROFactory.getCommentChildCommentROs(mineUser, comment.getUnionId(), showAll);
        socialCommentRO.setChildComments(socialCommentROS);

        log.debug("结束子评论：" + SystemUtil.getCurrentTimeSecond());
        if (!ObjectUtils.isEmpty(comment.getReplyCommentId())) {
            socialCommentRO.setReplyComment(ReplyCommentROFactory.newReplyCommentRO(comment.getReplyCommentId()));
        }
        return socialCommentRO;
    }

    public static List<SocialCommentRO> getTalkCommentROs(SocialUserDO mineUser, Integer talkId, Boolean showAllComment) {
        //10毫秒
        log.debug("开始查询comment" + new Date().getTime() / 1000);
        List<SocialCommentDO> commentDOS;
        if (showAllComment) {
            commentDOS = commentDao.queryTalkDetailComments(talkId);
        } else {
            commentDOS = commentDao.queryTalkComments(talkId);
        }
        List<SocialCommentRO> commentVOS = SocialCommentROFactory.newTalkCommentVOs(mineUser, commentDOS, showAllComment);
        log.debug("开始查询comment完成" + new Date().getTime() / 1000);
        return commentVOS;
    }

    public static List<SocialCommentRO> getCommentChildCommentROs(SocialUserDO mineUser, Integer commentId, Boolean showAllComment) {
        //10毫秒
        log.debug("开始查询comment" + new Date().getTime() / 1000);
        List<SocialCommentDO> commentDOS;
        if (showAllComment) {
            commentDOS = commentDao.queryCommentDetailChildComments(commentId);
        } else {
            commentDOS = commentDao.queryCommentChildComments(commentId);
        }
        List<SocialCommentRO> commentVOS = SocialCommentROFactory.newTalkCommentVOs(mineUser, commentDOS, showAllComment);
        log.debug("开始查询comment完成" + new Date().getTime() / 1000);
        return commentVOS;
    }


    private static List<SocialCommentRO> newTalkCommentVOs(SocialUserDO mineUser, List<SocialCommentDO> commentDOS, boolean showAll) {
        return commentDOS.stream()
                //过滤掉非自己的预审核状态的评论
                .filter(talkCommentDO -> {
                    // 用户不为 null && 自己的评论才显示
                    return (mineUser != null && talkCommentDO.getUserId().equals(mineUser.getUnionId()))
                            //或者评论的状态不为预审核
                            || !ContentStatus.preAudit.equals(talkCommentDO.getStatus());
                })
                .map(talkCommentDO -> SocialCommentROFactory.newTalkCommentRO(mineUser, talkCommentDO, showAll)).collect(Collectors.toList());
    }
}
