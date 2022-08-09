package com.socialuni.center.web.model.RO.talk;

import com.socialuni.center.web.model.RO.community.UniContentIdRO;
import com.socialuni.center.web.model.RO.user.CenterUserRO;
import com.socialuni.social.constant.ContentType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CenterReplyCommentRO extends UniContentIdRO {
    //如果这个评论 有parent，就代表已经是一个子评论，就不用把他设置为parent而是用它的parentId，他是否有parent
//    private Integer no;
    private CenterUserRO user;

    public CenterReplyCommentRO(CenterReplyCommentRO commentRO) {
        super(ContentType.comment, commentRO);
        this.user = new CenterUserRO(commentRO.getUser());
    }
}
