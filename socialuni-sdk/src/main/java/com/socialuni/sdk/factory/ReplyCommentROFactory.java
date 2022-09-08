package com.socialuni.sdk.factory;

import com.socialuni.sdk.factory.user.base.SocialUserROFactory;
import com.socialuni.sdk.dao.DO.comment.SocialCommentDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.model.RO.community.comment.SocialReplyCommentRO;
import com.socialuni.sdk.model.RO.user.base.SocialUserRO;
import com.socialuni.sdk.utils.CommentUtils;
import com.socialuni.sdk.utils.SocialuniUserUtil;
import org.springframework.stereotype.Component;

@Component
public class ReplyCommentROFactory {
    public static SocialReplyCommentRO newReplyCommentRO(Integer replyCommentId) {
        SocialCommentDO comment = CommentUtils.getNotNull(replyCommentId);
        SocialReplyCommentRO socialReplyCommentRO = new SocialReplyCommentRO();
        socialReplyCommentRO.setId(comment.getUnionId());
//        socialReplyCommentRO.setContentType(ContentType.comment);
//        replyCommentRO.setNo(comment.getNo());
        SocialuniUserDO commentUser = SocialuniUserUtil.getUserNotNull(comment.getUserId());
        SocialUserRO commentUserRO = SocialUserROFactory.getUserRO(commentUser);
        socialReplyCommentRO.setUser(commentUserRO);
        return socialReplyCommentRO;
    }
}
