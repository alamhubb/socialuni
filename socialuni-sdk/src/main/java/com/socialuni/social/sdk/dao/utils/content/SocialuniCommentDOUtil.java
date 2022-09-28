package com.socialuni.social.sdk.dao.utils.content;

import com.socialuni.social.sdk.constant.socialuni.ContentStatus;
import com.socialuni.social.sdk.dao.DO.SocialuniUnionIdDO;
import com.socialuni.social.sdk.dao.DO.community.comment.SocialuniCommentDO;
import com.socialuni.social.sdk.dao.repository.CommentRepository;
import com.socialuni.social.tance.util.DevAccountUtils;
import com.socialuni.social.sdk.utils.SocialuniUnionIdUtil;
import com.socialuni.social.common.exception.exception.SocialSystemException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Component
public class SocialuniCommentDOUtil {
    private static CommentRepository commentRepository;

    @Resource
    public void setCommentRepository(CommentRepository commentRepository) {
        SocialuniCommentDOUtil.commentRepository = commentRepository;
    }

    public static SocialuniCommentDO getNotCommentNull(Integer unionId) {
        SocialuniCommentDO commentDO = getAllowNull(unionId);
        if (commentDO == null) {
            throw new SocialSystemException("不存在的评论");
        }
        return commentDO;
    }

    public static SocialuniCommentDO getAllowNull(Integer unionId) {
        SocialuniUnionIdDO uniContentUnionIdDO = SocialuniUnionIdUtil.getUnionDOByUnionIdNotNull(unionId);
        SocialuniCommentDO commentDO = commentRepository.findOneByUnionId(unionId);
        if (commentDO == null) {
            if (Objects.equals(DevAccountUtils.getDevIdNotNull(), uniContentUnionIdDO.getFromDevId())) {
                throw new SocialSystemException("评论丢失了，请联系客服");
            }
        }
        return commentDO;
    }


    public static List<SocialuniCommentDO> getAll(Integer talkId) {
        return commentRepository.findTop50ByTalkIdAndStatusInAndParentCommentIdIsNullOrderByUpdateTimeDesc(talkId, ContentStatus.selfCanSeeContentStatus);
    }
}
