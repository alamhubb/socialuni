package com.socialuni.center.web.service.comment;


import com.socialuni.center.web.model.DO.comment.SocialCommentDO;
import com.socialuni.center.web.model.DO.talk.SocialTalkDO;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用来传输业务线用到的数据，避免重复查询数据库
 * @author qinkaiyuan
 * @date 2020-11-11 13:59
 */
@Data
@NoArgsConstructor
public class CommentAddLineTransfer {

    private SocialCommentDO commentDO;

    private SocialTalkDO talk;

    private SocialCommentDO parentComment;

    private SocialCommentDO replyComment;

    public CommentAddLineTransfer(SocialTalkDO talk, SocialCommentDO parentComment, SocialCommentDO replyComment) {
        this.talk = talk;
        this.parentComment = parentComment;
        this.replyComment = replyComment;
    }
}
