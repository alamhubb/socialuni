package com.socialuni.sdk.factory;

import com.socialuni.social.model.model.RO.community.comment.SocialCommentRO;
import com.socialuni.social.model.model.RO.user.SocialCommentUserRO;
import com.socialuni.sdk.constant.status.ContentStatus;
import com.socialuni.sdk.dao.CommentDao;
import com.socialuni.sdk.factory.user.SocialCommentUserROFactory;
import com.socialuni.sdk.model.DO.comment.CommentDO;
import com.socialuni.sdk.model.DO.user.UserDO;
import com.socialuni.sdk.repository.CommentRepository;
import com.socialuni.sdk.utils.SocialUserUtil;
import com.socialuni.sdk.utils.common.SystemUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class SocialTalkCommentROFactory {
    private static CommentRepository commentRepository;
    private static CommentDao commentDao;

    @Resource
    public void setCommentDao(CommentDao commentDao) {
        SocialTalkCommentROFactory.commentDao = commentDao;
    }

    @Resource
    public void setCommentRepository(CommentRepository commentRepository) {
        SocialTalkCommentROFactory.commentRepository = commentRepository;
    }

    public static SocialCommentRO newTalkCommentRO(UserDO mineUser, CommentDO comment, boolean showAll) {
        SocialCommentRO socialCommentRO = new SocialCommentRO();
        socialCommentRO.setId(comment.getId());
        socialCommentRO.setNo(comment.getNo());

        UserDO commentUser = SocialUserUtil.get(comment.getUserId());
        SocialCommentUserRO commentUserRO = SocialCommentUserROFactory.newCommentUserRO(commentUser);
        socialCommentRO.setUser(commentUserRO);

        socialCommentRO.setContent(comment.getContent());
        socialCommentRO.setHugNum(comment.getHugNum());
        socialCommentRO.setCreateTime(comment.getCreateTime());
        socialCommentRO.setChildCommentNum(comment.getChildCommentNum());

        List<SocialCommentRO> socialCommentROS = SocialTalkCommentROFactory.getCommentChildCommentROs(mineUser, comment.getId(), showAll);
        socialCommentRO.setChildComments(socialCommentROS);

        log.debug("结束子评论：" + SystemUtil.getCurrentTimeSecond());
        if (!ObjectUtils.isEmpty(comment.getReplyCommentId())) {
            socialCommentRO.setReplyComment(ReplyCommentROFactory.newReplyCommentRO(comment.getReplyCommentId()));
        }
        return socialCommentRO;
    }

    public static List<SocialCommentRO> getTalkCommentROs(UserDO mineUser, Integer talkId, Boolean showAllComment) {
        //10毫秒
        log.debug("开始查询comment" + new Date().getTime() / 1000);
        List<CommentDO> commentDOS;
        if (showAllComment) {
            commentDOS = commentDao.queryTalkDetailComments(talkId);
        } else {
            commentDOS = commentDao.queryTalkComments(talkId);
        }
        List<SocialCommentRO> commentVOS = SocialTalkCommentROFactory.newTalkCommentVOs(mineUser, commentDOS, showAllComment);
        log.debug("开始查询comment完成" + new Date().getTime() / 1000);
        return commentVOS;
    }

    public static List<SocialCommentRO> getCommentChildCommentROs(UserDO mineUser, Integer commentId, Boolean showAllComment) {
        //10毫秒
        log.debug("开始查询comment" + new Date().getTime() / 1000);
        List<CommentDO> commentDOS;
        if (showAllComment) {
            commentDOS = commentDao.queryCommentDetailChildComments(commentId);
        } else {
            commentDOS = commentDao.queryCommentChildComments(commentId);
        }
        List<SocialCommentRO> commentVOS = SocialTalkCommentROFactory.newTalkCommentVOs(mineUser, commentDOS, showAllComment);
        log.debug("开始查询comment完成" + new Date().getTime() / 1000);
        return commentVOS;
    }


    private static List<SocialCommentRO> newTalkCommentVOs(UserDO mineUser, List<CommentDO> commentDOS, boolean showAll) {
        return commentDOS.stream()
                //过滤掉非自己的预审核状态的评论
                .filter(talkCommentDO -> {
                    // 用户不为 null && 自己的评论才显示
                    return (mineUser != null && talkCommentDO.getUserId().equals(mineUser.getId()))
                            //或者评论的状态不为预审核
                            || !ContentStatus.preAudit.equals(talkCommentDO.getStatus());
                })
                .map(talkCommentDO -> SocialTalkCommentROFactory.newTalkCommentRO(mineUser, talkCommentDO, showAll)).collect(Collectors.toList());
    }
}
