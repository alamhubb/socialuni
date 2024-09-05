package com.socialuni.social.community.sdk.dao;

import com.socialuni.social.common.api.exception.exception.SocialSystemException;
import com.socialuni.social.community.sdk.dao.DO.SocialuniCommentDO;
import com.socialuni.social.community.sdk.repository.CommentRepository;
import com.socialuni.social.common.api.enumeration.ContentStatus;
import com.socialuni.social.tance.dev.facade.SocialuniUnionIdFacede;
import com.socialuni.social.tance.dev.facade.DevAccountFacade;
import com.socialuni.social.tance.dev.model.SocialuniUnionIdModler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Component
public class SocialuniCommentDOUtil {
    private static CommentRepository commentApi;

    @Resource
    public void setCommentRepository(CommentRepository commentApi) {
        SocialuniCommentDOUtil.commentApi = commentApi;
    }

    public static SocialuniCommentDO getNotCommentNull(Long unionId) {
        SocialuniCommentDO commentDO = getAllowNull(unionId);
        if (commentDO == null) {
            throw new SocialSystemException("不存在的评论");
        }
        return commentDO;
    }

    public static SocialuniCommentDO getAllowNull(Long unionId) {
        SocialuniUnionIdModler uniContentUnionIdDO = SocialuniUnionIdFacede.getUnionDOByUnionIdAllowNull(unionId);
        if (uniContentUnionIdDO == null) {
            return null;
        }
        SocialuniCommentDO commentDO = commentApi.findOneByUnionId(unionId);
        if (commentDO == null) {
            if (Objects.equals(DevAccountFacade.getDevIdNullElseCenterDevId(), uniContentUnionIdDO.getFromDevId())) {
                throw new SocialSystemException("评论丢失了，请联系客服");
            }
        }
        return commentDO;
    }


    public static List<? extends SocialuniCommentDO> getAll(Long talkId) {
        return commentApi.findTop50ByTalkIdAndStatusInAndParentCommentIdIsNullOrderByUpdateTimeDesc(talkId, ContentStatus.selfCanSeeContentStatus);
    }
}
