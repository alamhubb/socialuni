package com.socialuni.social.model.model.RO.community.comment;

import com.socialuni.social.model.model.QO.user.SocialId;
import com.socialuni.social.model.model.RO.user.SocialCommentUserRO;
import lombok.Data;

@Data
public class ReplyCommentRO extends SocialId {
    //如果这个评论 有parent，就代表已经是一个子评论，就不用把他设置为parent而是用它的parentId，他是否有parent
//    private Integer no;

    private SocialCommentUserRO user;
}
