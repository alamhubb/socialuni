package com.socialuni.social.sdk.logic.service.comment;


import com.socialuni.social.community.sdk.entity.SocialuniCommentDO;
import com.socialuni.social.community.sdk.entity.SocialuniTalkDO;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用来传输业务线用到的数据，避免重复查询数据库
 *
 * @author qinkaiyuan
 * @date 2020-11-11 13:59
 */
@Data
@NoArgsConstructor
public class CommentAddLineTransfer {

    private SocialuniCommentDO commentDO;

    private SocialuniTalkDO talk;

    private SocialuniCommentDO parentComment;

    private SocialuniCommentDO replyComment;

    public CommentAddLineTransfer(SocialuniTalkDO talk, SocialuniCommentDO parentComment, SocialuniCommentDO replyComment) {
        this.talk = talk;
        this.parentComment = parentComment;
        this.replyComment = replyComment;
    }
}
