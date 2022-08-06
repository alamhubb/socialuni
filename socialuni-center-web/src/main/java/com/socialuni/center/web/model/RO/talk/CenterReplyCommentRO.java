package com.socialuni.center.web.model.RO.talk;

import com.socialuni.center.web.model.RO.community.UniContentIdRO;
import com.socialuni.center.web.model.RO.user.CenterUserRO;
import lombok.Data;

@Data
public class CenterReplyCommentRO extends UniContentIdRO {
    //如果这个评论 有parent，就代表已经是一个子评论，就不用把他设置为parent而是用它的parentId，他是否有parent
//    private Integer no;
    private CenterUserRO user;
}
