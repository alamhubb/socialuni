package com.socialuni.social.common.sdk.logic.factory;

import com.socialuni.social.community.sdk.dao.DO.SocialuniCommentDO;
import com.socialuni.social.common.api.enumeration.ContentStatus;
import com.socialuni.social.common.sdk.dao.CommentDao;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.common.sdk.logic.factory.RO.comment.SocialuniReplyCommentROFactory;
import com.socialuni.social.user.sdk.model.factory.SocialuniUserROFactory;
import com.socialuni.social.common.sdk.model.RO.talk.SocialuniCommentRO;
import com.socialuni.social.common.api.model.user.SocialuniUserRO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.common.sdk.utils.SystemUtil;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
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
    private static CommentDao commentDao;

    @Resource
    public void setCommentDao(CommentDao commentDao) {
        SocialCommentROFactory.commentDao = commentDao;
    }


    public static SocialuniCommentRO newTalkCommentRO(SocialuniUserDo mineUser, SocialuniCommentDO comment, boolean showAll) {
        SocialuniCommentRO socialCommentRO = new SocialuniCommentRO();

        String uid = SocialuniUnionIdFacede.getUuidByUnionIdNotNull(comment.getUnionId());

        socialCommentRO.setId(uid);
        socialCommentRO.setNo(comment.getNo());

        SocialuniUserDo commentUser = SocialuniUserUtil.getAndCheckUserNotNull(comment.getUserId());
        SocialuniUserRO commentUserRO = SocialuniUserROFactory.getUserRO(commentUser, mineUser);
        socialCommentRO.setUser(commentUserRO);

        socialCommentRO.setContent(comment.getContent());
//        socialCommentRO.setContentType(comment.getContentType());
        socialCommentRO.setHugNum(comment.getHugNum());
        socialCommentRO.setReportNum(comment.getReportNum());
        socialCommentRO.setCreateTime(comment.getCreateTime());
        socialCommentRO.setChildCommentNum(comment.getChildCommentNum());

        List<SocialuniCommentRO> socialCommentROS = SocialCommentROFactory.getCommentChildCommentROs(mineUser, comment.getUnionId(), showAll);
        socialCommentRO.setChildComments(socialCommentROS);

        log.debug("结束子评论：" + SystemUtil.getCurrentTimeSecond());
        if (!ObjectUtils.isEmpty(comment.getReplyCommentId())) {
            socialCommentRO.setReplyComment(SocialuniReplyCommentROFactory.getReplyCommentRO(comment.getReplyCommentId()));
        }
        return socialCommentRO;
    }

    public static List<SocialuniCommentRO> getTalkCommentROs(SocialuniUserDo mineUser, Integer talkId, Boolean showAllComment) {
        //10毫秒
        log.debug("开始查询comment" + new Date().getTime() / 1000);
        List<? extends SocialuniCommentDO> commentDOS;
        if (showAllComment) {
            commentDOS = commentDao.queryTalkDetailComments(talkId);
        } else {
            commentDOS = commentDao.queryTalkComments(talkId);
        }
        List<SocialuniCommentRO> commentVOS = SocialCommentROFactory.newTalkCommentVOs(mineUser, commentDOS, showAllComment);
        log.debug("开始查询comment完成" + new Date().getTime() / 1000);
        return commentVOS;
    }

    public static List<SocialuniCommentRO> getCommentChildCommentROs(SocialuniUserDo mineUser, Integer commentId, Boolean showAllComment) {
        //10毫秒
        log.debug("开始查询comment" + new Date().getTime() / 1000);
        List<? extends SocialuniCommentDO> commentDOS;
        if (showAllComment) {
            commentDOS = commentDao.queryCommentDetailChildComments(commentId);
        } else {
            commentDOS = commentDao.queryCommentChildComments(commentId);
        }
        List<SocialuniCommentRO> commentVOS = SocialCommentROFactory.newTalkCommentVOs(mineUser, commentDOS, showAllComment);
        log.debug("开始查询comment完成" + new Date().getTime() / 1000);
        return commentVOS;
    }


    private static List<SocialuniCommentRO> newTalkCommentVOs(SocialuniUserDo mineUser, List<? extends SocialuniCommentDO> commentDOS, boolean showAll) {
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
