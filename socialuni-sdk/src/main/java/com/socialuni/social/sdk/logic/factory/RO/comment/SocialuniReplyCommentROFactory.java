package com.socialuni.social.sdk.logic.factory.RO.comment;

import com.socialuni.social.community.sdk.model.SocialuniCommentModel;
import com.socialuni.social.user.sdk.model.SocialuniUserModel;
import com.socialuni.social.sdk.dao.utils.content.SocialuniCommentDOUtil;
import com.socialuni.social.sdk.logic.factory.RO.user.SocialuniUserROFactory;
import com.socialuni.social.sdk.model.RO.talk.SocialuniReplyCommentRO;
import com.socialuni.social.sdk.model.RO.user.SocialuniUserRO;
import com.socialuni.social.sdk.utils.SocialuniUserUtil;
import org.springframework.stereotype.Component;

@Component
public class SocialuniReplyCommentROFactory {
    public static SocialuniReplyCommentRO getReplyCommentRO(Integer replyCommentId) {
        SocialuniCommentModel comment = SocialuniCommentDOUtil.getNotCommentNull(replyCommentId);
        SocialuniReplyCommentRO socialReplyCommentRO = new SocialuniReplyCommentRO();
        socialReplyCommentRO.setId(comment.getUnionId());
//        socialReplyCommentRO.setContentType(ContentType.comment);
//        replyCommentRO.setNo(comment.getNo());
        SocialuniUserModel commentUser = SocialuniUserUtil.getUserNotNull(comment.getUserId());
        SocialuniUserRO commentUserRO = SocialuniUserROFactory.getUserRO(commentUser);
        socialReplyCommentRO.setUser(commentUserRO);
        return socialReplyCommentRO;
    }
}
