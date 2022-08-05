package com.socialuni.center.web.service.comment;


import com.socialuni.center.web.model.DO.comment.CommentDO;
import com.socialuni.center.web.model.DO.talk.TalkDO;
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

    private CommentDO commentDO;

    private TalkDO talk;

    private CommentDO parentComment;

    private CommentDO replyComment;

    public CommentAddLineTransfer(TalkDO talk, CommentDO parentComment, CommentDO replyComment) {
        this.talk = talk;
        this.parentComment = parentComment;
        this.replyComment = replyComment;
    }
}
