package com.socialuni.social.community.sdk.model.RO.talk;

import com.socialuni.social.common.api.model.user.SocialuniUserShowRO;
import lombok.Data;

@Data
public class SocialuniReplyCommentRO {
    Long id;
    //如果这个评论 有parent，就代表已经是一个子评论，就不用把他设置为parent而是用它的parentId，他是否有parent
//    private Integer no;
    private SocialuniUserShowRO user;
}
