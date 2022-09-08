package com.socialuni.sdk.dao;

import com.socialuni.sdk.model.DO.comment.SocialCommentDO;
import com.socialuni.sdk.constant.socialuni.ContentStatus;
import com.socialuni.sdk.dao.repository.CommentRepository;
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
    CommentRepository commentRepository;

    public List<SocialCommentDO> queryTalkComments(Integer talkId) {
        return commentRepository.findTop5ByTalkIdAndStatusInAndParentCommentIdIsNullOrderByUpdateTimeDesc(talkId, ContentStatus.selfCanSeeContentStatus);
    }

    public List<SocialCommentDO> queryTalkDetailComments(Integer talkId) {
        return commentRepository.findTop50ByTalkIdAndStatusInAndParentCommentIdIsNullOrderByUpdateTimeDesc(talkId, ContentStatus.selfCanSeeContentStatus);
    }


    public List<SocialCommentDO> queryCommentChildComments(Integer commentId) {
        return commentRepository.findTop3ByParentCommentIdAndStatusInOrderByUpdateTimeDesc(commentId, ContentStatus.selfCanSeeContentStatus);
    }

    public List<SocialCommentDO> queryCommentDetailChildComments(Integer commentId) {
        return commentRepository.findTop50ByParentCommentIdAndStatusInOrderByUpdateTimeDesc(commentId, ContentStatus.selfCanSeeContentStatus);
    }
}