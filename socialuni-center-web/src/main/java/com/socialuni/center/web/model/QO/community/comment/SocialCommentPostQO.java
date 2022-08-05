package com.socialuni.center.web.model.QO.community.comment;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * TODO〈一句话功能简述〉
 * TODO〈功能详细描述〉
 *
 * @author qinkaiyuan
 * @since TODO[起始版本号]
 */
@Data
@NoArgsConstructor
public class SocialCommentPostQO {
    private String content;
    @NotNull
    private Integer talkId;
    private Integer commentId;
    private Integer replyCommentId;
    private String socialuniUid;

    public SocialCommentPostQO(String content, Integer talkId, Integer commentId, Integer replyCommentId) {
        this.content = content;
        this.talkId = talkId;
        this.commentId = commentId;
        this.replyCommentId = replyCommentId;
    }
}
