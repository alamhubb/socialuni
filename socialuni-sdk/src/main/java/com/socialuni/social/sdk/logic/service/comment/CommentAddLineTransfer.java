package com.socialuni.social.sdk.logic.service.comment;


import com.socialuni.social.community.sdk.model.SocialuniCommentModel;
import com.socialuni.social.community.sdk.model.SocialuniTalkModel;
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

    private SocialuniCommentModel commentDO;

    private SocialuniTalkModel talk;

    private SocialuniCommentModel parentComment;

    private SocialuniCommentModel replyComment;

    public CommentAddLineTransfer(SocialuniTalkModel talk, SocialuniCommentModel parentComment, SocialuniCommentModel replyComment) {
        this.talk = talk;
        this.parentComment = parentComment;
        this.replyComment = replyComment;
    }
}
