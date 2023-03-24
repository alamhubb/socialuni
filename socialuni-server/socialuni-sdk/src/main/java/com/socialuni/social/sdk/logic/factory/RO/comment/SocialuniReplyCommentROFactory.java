package com.socialuni.social.sdk.logic.factory.RO.comment;

import com.socialuni.social.community.sdk.entity.SocialuniCommentDO;
import com.socialuni.social.sdk.dao.utils.content.SocialuniCommentDOUtil;
import com.socialuni.social.user.sdk.model.factory.SocialuniUserROFactory;
import com.socialuni.social.sdk.model.RO.talk.SocialuniReplyCommentRO;
import com.socialuni.social.common.api.model.user.SocialuniUserRO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.common.sdk.dao.DO.keywords.SocialuniUserDo;
import org.springframework.stereotype.Component;

@Component
public class SocialuniReplyCommentROFactory {
    public static SocialuniReplyCommentRO getReplyCommentRO(Integer replyCommentId) {
        SocialuniCommentDO comment = SocialuniCommentDOUtil.getNotCommentNull(replyCommentId);
        SocialuniReplyCommentRO socialReplyCommentRO = new SocialuniReplyCommentRO();
        socialReplyCommentRO.setId(comment.getUnionId());
//        socialReplyCommentRO.setContentType(ContentType.comment);
//        replyCommentRO.setNo(comment.getNo());
        SocialuniUserDo commentUser = SocialuniUserUtil.getUserNotNull(comment.getUserId());
        SocialuniUserRO commentUserRO = SocialuniUserROFactory.getUserRO(commentUser, SocialuniUserUtil.getMineUserNotNull());
        socialReplyCommentRO.setUser(commentUserRO);
        return socialReplyCommentRO;
    }
}
