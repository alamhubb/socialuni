package com.socialuni.social.sdk.dao;

import com.socialuni.social.sdk.constant.socialuni.ContentStatus;
import com.socialuni.social.community.sdk.model.SocialuniCommentModel;
import com.socialuni.social.community.sdk.api.CommentApi;
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
    CommentApi commentApi;

    public List<?  extends SocialuniCommentModel> queryTalkComments(Integer talkId) {
        return commentApi.findTop5ByTalkIdAndStatusInAndParentCommentIdIsNullOrderByUpdateTimeDesc(talkId, ContentStatus.selfCanSeeContentStatus);
    }

    public List<?  extends SocialuniCommentModel> queryTalkDetailComments(Integer talkId) {
        return commentApi.findTop50ByTalkIdAndStatusInAndParentCommentIdIsNullOrderByUpdateTimeDesc(talkId, ContentStatus.selfCanSeeContentStatus);
    }


    public List<?  extends SocialuniCommentModel> queryCommentChildComments(Integer commentId) {
        return commentApi.findTop3ByParentCommentIdAndStatusInOrderByUpdateTimeDesc(commentId, ContentStatus.selfCanSeeContentStatus);
    }

    public List<?  extends SocialuniCommentModel> queryCommentDetailChildComments(Integer commentId) {
        return commentApi.findTop50ByParentCommentIdAndStatusInOrderByUpdateTimeDesc(commentId, ContentStatus.selfCanSeeContentStatus);
    }
}