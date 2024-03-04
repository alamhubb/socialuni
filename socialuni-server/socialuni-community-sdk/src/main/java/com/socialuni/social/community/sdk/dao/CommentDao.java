package com.socialuni.social.community.sdk.dao;

import com.socialuni.social.community.sdk.dao.DO.SocialuniCommentDO;
import com.socialuni.social.community.sdk.repository.CommentRepository;
import com.socialuni.social.common.api.enumeration.ContentStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 不需要像帖子一样，每次有回复都刷新，因为不愁看，且你评论后的，有动静你会有通知
 */
@Component
@Slf4j
public class CommentDao {
    @Resource
    CommentRepository commentApi;

    public List<?  extends SocialuniCommentDO> queryTalkComments(Integer talkId) {
        return commentApi.findTop5ByTalkIdAndStatusInAndParentCommentIdIsNullOrderByUpdateTimeDesc(talkId, ContentStatus.selfCanSeeContentStatus);
    }

    public List<?  extends SocialuniCommentDO> queryTalkDetailComments(Integer talkId) {
        return commentApi.findTop50ByTalkIdAndStatusInAndParentCommentIdIsNullOrderByUpdateTimeDesc(talkId, ContentStatus.selfCanSeeContentStatus);
    }


    public List<?  extends SocialuniCommentDO> queryCommentChildComments(Integer commentId) {
        return commentApi.findTop3ByParentCommentIdAndStatusInOrderByUpdateTimeDesc(commentId, ContentStatus.selfCanSeeContentStatus);
    }

    public List<?  extends SocialuniCommentDO> queryCommentDetailChildComments(Integer commentId) {
        return commentApi.findTop50ByParentCommentIdAndStatusInOrderByUpdateTimeDesc(commentId, ContentStatus.selfCanSeeContentStatus);
    }
}