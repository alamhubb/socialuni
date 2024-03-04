package com.socialuni.social.community.sdk.model.QO.comment;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * TODO〈一句话功能简述〉
 * TODO〈功能详细描述〉
 *
 * @author qinkaiyuan
 * @since TODO[起始版本号]
 */
@Data
public class CommentAddVO {
    private String content;
    @NotNull
    private Integer talkId;
    private Integer commentId;
    private Integer replyCommentId;
}
