package com.socialuni.center.web.utils;

import com.socialuni.social.constant.ContentStatus;
import com.socialuni.social.exception.SocialSystemException;
import com.socialuni.center.web.model.DO.comment.SocialCommentDO;
import com.socialuni.center.web.repository.CommentRepository;
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

    public static SocialCommentDO get(Integer commentId) {
        if (commentId == null) {
            throw new SocialSystemException("commentId为空");
        }
        SocialCommentDO commentDO = commentRepository.findOneByUnionId(commentId);
        if (commentDO == null) {
            throw new SocialSystemException("comment为空");
        }
        return commentDO;
    }

    public static List<SocialCommentDO> getAll(Integer talkId) {
        return commentRepository.findTop50ByTalkIdAndStatusInAndParentCommentIdIsNullOrderByUpdateTimeDesc(talkId, ContentStatus.selfCanSeeContentStatus);
    }
}
