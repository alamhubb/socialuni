package com.socialuni.sdk.factory;

import com.socialuni.sdk.factory.user.SocialCommentUserROFactory;
import com.socialuni.sdk.model.DO.comment.CommentDO;
import com.socialuni.social.model.model.RO.user.SocialCommentUserRO;
import com.socialuni.social.model.model.RO.community.comment.ReplyCommentRO;
import com.socialuni.sdk.utils.CommentUtils;
import org.springframework.stereotype.Component;

@Component
public class ReplyCommentROFactory {
    public static ReplyCommentRO newReplyCommentRO(Integer replyCommentId) {
        CommentDO comment = CommentUtils.get(replyCommentId);
        ReplyCommentRO replyCommentRO = new ReplyCommentRO();
        replyCommentRO.setId(comment.getId());
//        replyCommentRO.setNo(comment.getNo());
        SocialCommentUserRO commentUserRO = SocialCommentUserROFactory.newCommentUserRO(comment.getUserId());
        replyCommentRO.setUser(commentUserRO);
        return replyCommentRO;
    }
}
