package com.socialuni.center.web.factory;

import com.socialuni.center.web.factory.user.base.SocialUserROFactory;
import com.socialuni.center.web.model.DO.comment.CommentDO;
import com.socialuni.center.web.model.DO.user.SocialUserDO;
import com.socialuni.center.web.utils.CommentUtils;
import com.socialuni.center.web.utils.SocialUserUtil;
import com.socialuni.center.web.model.RO.community.comment.SocialReplyCommentRO;
import com.socialuni.center.web.model.RO.user.base.SocialUserRO;
import com.socialuni.social.constant.ContentType;
import org.springframework.stereotype.Component;

@Component
public class ReplyCommentROFactory {
    public static SocialReplyCommentRO newReplyCommentRO(Integer replyCommentId) {
        CommentDO comment = CommentUtils.get(replyCommentId);
        SocialReplyCommentRO socialReplyCommentRO = new SocialReplyCommentRO();
        socialReplyCommentRO.setId(comment.getId());
        socialReplyCommentRO.setContentType(ContentType.comment);
//        replyCommentRO.setNo(comment.getNo());
        SocialUserDO commentUser = SocialUserUtil.getNotNull(comment.getUserId());
        SocialUserRO commentUserRO = SocialUserROFactory.getUserRO(commentUser);
        socialReplyCommentRO.setUser(commentUserRO);
        return socialReplyCommentRO;
    }
}
