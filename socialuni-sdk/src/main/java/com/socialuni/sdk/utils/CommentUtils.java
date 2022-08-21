package com.socialuni.sdk.utils;

import com.socialuni.sdk.model.DO.UniContentUnionIdDO;
import com.socialuni.sdk.model.DO.comment.SocialCommentDO;
import com.socialuni.sdk.repository.CommentRepository;
import com.socialuni.social.constant.ContentStatus;
import com.socialuni.social.exception.SocialSystemException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Component
public class CommentUtils {
    private static CommentRepository commentRepository;

    @Resource
    public void setCommentRepository(CommentRepository commentRepository) {
        CommentUtils.commentRepository = commentRepository;
    }

    public static SocialCommentDO getNotNull(Integer unionId) {
        SocialCommentDO commentDO = getAllowNull(unionId);
        if (commentDO == null) {
            throw new SocialSystemException("不存在的评论");
        }
        return commentDO;
    }

    public static SocialCommentDO getAllowNull(Integer unionId) {
        UniContentUnionIdDO uniContentUnionIdDO = UnionIdDbUtil.getUnionDOByUnionIdNotNull(unionId);
        SocialCommentDO commentDO = commentRepository.findOneByUnionId(unionId);
        if (commentDO == null) {
            if (Objects.equals(DevAccountUtils.getDevIdNotNull(), uniContentUnionIdDO.getFromDevId())) {
                throw new SocialSystemException("评论丢失了，请联系客服");
            }
        }
        return commentDO;
    }


    public static List<SocialCommentDO> getAll(Integer talkId) {
        return commentRepository.findTop50ByTalkIdAndStatusInAndParentCommentIdIsNullOrderByUpdateTimeDesc(talkId, ContentStatus.selfCanSeeContentStatus);
    }
}
