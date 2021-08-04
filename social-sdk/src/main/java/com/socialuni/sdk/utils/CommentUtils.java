package com.socialuni.sdk.utils;

import com.socialuni.sdk.constant.status.ContentStatus;
import com.socialuni.sdk.exception.SocialSystemException;
import com.socialuni.sdk.model.DO.comment.CommentDO;
import com.socialuni.sdk.repository.CommentRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class CommentUtils {
    private static CommentRepository commentRepository;

    @Resource
    public void setCommentRepository(CommentRepository commentRepository) {
        CommentUtils.commentRepository = commentRepository;
    }

    public static CommentDO get(Integer commentId) {
        if (commentId == null) {
            throw new SocialSystemException("commentId为空");
        }
        CommentDO commentDO = commentRepository.findOneById(commentId);
        if (commentDO == null) {
            throw new SocialSystemException("comment为空");
        }
        return commentDO;
    }

    public static List<CommentDO> getAll(Integer talkId) {
        return commentRepository.findTop50ByTalkIdAndStatusInAndParentCommentIdIsNullOrderByUpdateTimeDesc(talkId, ContentStatus.selfCanSeeContentStatus);
    }
}
