package com.socialuni.social.sdk.factory;

import com.socialuni.social.sdk.factory.user.base.SocialUserROFactory;
import com.socialuni.social.entity.model.DO.comment.CommentDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.sdk.utils.CommentUtils;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import com.socialuni.social.model.model.RO.community.comment.SocialReplyCommentRO;
import com.socialuni.social.model.model.RO.user.base.SocialUserRO;
import org.springframework.stereotype.Component;

@Component
public class ReplyCommentROFactory {
    public static SocialReplyCommentRO newReplyCommentRO(Integer replyCommentId) {
        CommentDO comment = CommentUtils.get(replyCommentId);
        SocialReplyCommentRO socialReplyCommentRO = new SocialReplyCommentRO();
        socialReplyCommentRO.setId(comment.getId());
//        replyCommentRO.setNo(comment.getNo());
        UserDO commentUser = SocialUserUtil.getNotNull(comment.getUserId());
        SocialUserRO commentUserRO = SocialUserROFactory.getUserRO(commentUser);
        socialReplyCommentRO.setUser(commentUserRO);
        return socialReplyCommentRO;
    }
}
