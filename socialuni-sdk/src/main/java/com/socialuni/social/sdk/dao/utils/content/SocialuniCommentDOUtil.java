package com.socialuni.social.sdk.dao.utils.content;

import com.socialuni.social.sdk.constant.socialuni.ContentStatus;
import com.socialuni.social.tance.sdk.model.SocialuniUnionIdModler;
import com.socialuni.social.community.sdk.model.SocialuniCommentModel;
import com.socialuni.social.community.sdk.api.CommentInterface;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.common.sdk.exception.exception.SocialSystemException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Component
public class SocialuniCommentDOUtil {
    private static CommentInterface commentApi;

    @Resource
    public void setCommentRepository(CommentInterface commentApi) {
        SocialuniCommentDOUtil.commentApi = commentApi;
    }

    public static SocialuniCommentModel getNotCommentNull(Integer unionId) {
        SocialuniCommentModel commentDO = getAllowNull(unionId);
        if (commentDO == null) {
            throw new SocialSystemException("不存在的评论");
        }
        return commentDO;
    }

    public static SocialuniCommentModel getAllowNull(Integer unionId) {
        SocialuniUnionIdModler uniContentUnionIdDO = SocialuniUnionIdFacede.getUnionDOByUnionIdNotNull(unionId);
        SocialuniCommentModel commentDO = commentApi.findOneByUnionId(unionId);
        if (commentDO == null) {
            if (Objects.equals(DevAccountFacade.getDevIdNotNull(), uniContentUnionIdDO.getFromDevId())) {
                throw new SocialSystemException("评论丢失了，请联系客服");
            }
        }
        return commentDO;
    }


    public static List<?  extends SocialuniCommentModel> getAll(Integer talkId) {
        return commentApi.findTop50ByTalkIdAndStatusInAndParentCommentIdIsNullOrderByUpdateTimeDesc(talkId, ContentStatus.selfCanSeeContentStatus);
    }
}
