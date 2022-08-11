package com.socialuni.sdk.utils;

import com.socialuni.sdk.model.DO.comment.SocialCommentDO;
import com.socialuni.sdk.repository.CommentRepository;
import com.socialuni.social.constant.ContentStatus;
import com.socialuni.social.exception.SocialSystemException;
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

    public static SocialCommentDO get(Integer unionId) {
        SocialCommentDO commentDO = commentRepository.findOneByUnionId(unionId);
        if (commentDO == null) {
            throw new SocialSystemException("不存在的评论");
        }
        return commentDO;
    }

    public static List<SocialCommentDO> getAll(Integer talkId) {
        return commentRepository.findTop50ByTalkIdAndStatusInAndParentCommentIdIsNullOrderByUpdateTimeDesc(talkId, ContentStatus.selfCanSeeContentStatus);
    }
}
