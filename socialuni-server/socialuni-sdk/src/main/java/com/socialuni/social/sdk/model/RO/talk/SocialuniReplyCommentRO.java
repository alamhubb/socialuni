package com.socialuni.social.sdk.model.RO.talk;

import com.socialuni.social.common.api.entity.SocialuniBaseDO;
import com.socialuni.social.common.api.model.user.SocialuniUserRO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SocialuniReplyCommentRO extends SocialuniBaseDO {
    //如果这个评论 有parent，就代表已经是一个子评论，就不用把他设置为parent而是用它的parentId，他是否有parent
//    private Integer no;
    private SocialuniUserRO user;
}
