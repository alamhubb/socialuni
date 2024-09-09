package com.socialuni.social.community.sdk.logic.factory;

import com.socialuni.social.community.sdk.dao.DO.SocialuniCommentDO;
import com.socialuni.social.community.sdk.dao.SocialuniCommentDOUtil;
import com.socialuni.social.user.sdk.model.factory.SocialuniUserShowROFactory;
import com.socialuni.social.community.sdk.model.RO.talk.SocialuniReplyCommentRO;
import com.socialuni.social.common.api.model.user.SocialuniUserShowRO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import org.springframework.stereotype.Component;

@Component
public class SocialuniReplyCommentROFactory {
    public static SocialuniReplyCommentRO getReplyCommentRO(Long replyCommentId) {
        SocialuniCommentDO comment = SocialuniCommentDOUtil.getNotCommentNull(replyCommentId);
        SocialuniReplyCommentRO socialReplyCommentRO = new SocialuniReplyCommentRO();
        socialReplyCommentRO.setId(comment.getUnionId());
//        socialReplyCommentRO.setContentType(ContentType.comment);
//        replyCommentRO.setNo(comment.getNo());
        SocialuniUserDo commentUser = SocialuniUserUtil.getUserNotNull(comment.getUserId());
        SocialuniUserShowRO commentUserRO = SocialuniUserShowROFactory.getUserRO(commentUser, SocialuniUserUtil.getMineUserIdNotNull());
        socialReplyCommentRO.setUser(commentUserRO);
        return socialReplyCommentRO;
    }
}
