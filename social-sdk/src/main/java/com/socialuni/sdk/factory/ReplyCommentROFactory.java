package com.socialuni.sdk.factory;

import com.socialuni.sdk.factory.user.SocialCommentUserROFactory;
import com.socialuni.sdk.model.DO.comment.CommentDO;
import com.socialuni.social.model.model.RO.community.comment.SocialReplyCommentRO;
import com.socialuni.sdk.utils.CommentUtils;
import org.springframework.stereotype.Component;

@Component
public class ReplyCommentROFactory {
    public static SocialReplyCommentRO newReplyCommentRO(Integer replyCommentId) {
        CommentDO comment = CommentUtils.get(replyCommentId);
        SocialReplyCommentRO socialReplyCommentRO = new SocialReplyCommentRO();
        socialReplyCommentRO.setId(comment.getId());
//        replyCommentRO.setNo(comment.getNo());
        SocialCommentUserRO commentUserRO = SocialCommentUserROFactory.newCommentUserRO(comment.getUserId());
        socialReplyCommentRO.setUser(commentUserRO);
        return socialReplyCommentRO;
    }
}
