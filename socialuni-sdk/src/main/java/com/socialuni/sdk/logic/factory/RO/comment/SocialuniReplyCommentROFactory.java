package com.socialuni.sdk.logic.factory.RO.comment;

import com.socialuni.sdk.dao.DO.comment.SocialCommentDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.logic.factory.RO.user.SocialuniUserROFactory;
import com.socialuni.sdk.model.RO.talk.SocialuniReplyCommentRO;
import com.socialuni.sdk.model.RO.user.SocialuniUserRO;
import com.socialuni.sdk.utils.CommentUtils;
import com.socialuni.sdk.utils.SocialuniUserUtil;
import org.springframework.stereotype.Component;

@Component
public class SocialuniReplyCommentROFactory {
    public static SocialuniReplyCommentRO getReplyCommentRO(Integer replyCommentId) {
        SocialCommentDO comment = CommentUtils.getNotNull(replyCommentId);
        SocialuniReplyCommentRO socialReplyCommentRO = new SocialuniReplyCommentRO();
        socialReplyCommentRO.setId(comment.getUnionId());
//        socialReplyCommentRO.setContentType(ContentType.comment);
//        replyCommentRO.setNo(comment.getNo());
        SocialuniUserDO commentUser = SocialuniUserUtil.getUserNotNull(comment.getUserId());
        SocialuniUserRO commentUserRO = SocialuniUserROFactory.getUserRO(commentUser);
        socialReplyCommentRO.setUser(commentUserRO);
        return socialReplyCommentRO;
    }
}
