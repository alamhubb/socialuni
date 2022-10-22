package com.socialuni.social.sdk.model.RO.talk;

import com.socialuni.social.common.dao.DO.SocialCommonBaseDO;
import com.socialuni.social.sdk.model.RO.user.SocialuniUserRO;
import lombok.Data;

@Data
public class SocialuniReplyCommentRO extends SocialCommonBaseDO {
    //如果这个评论 有parent，就代表已经是一个子评论，就不用把他设置为parent而是用它的parentId，他是否有parent
//    private Integer no;
    private SocialuniUserRO user;
}
