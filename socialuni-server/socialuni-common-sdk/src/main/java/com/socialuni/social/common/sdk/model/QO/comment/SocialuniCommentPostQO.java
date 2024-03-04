package com.socialuni.social.common.sdk.model.QO.comment;

import com.socialuni.social.common.sdk.model.QO.ContentAddQO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * TODO〈一句话功能简述〉
 * TODO〈功能详细描述〉
 *
 * @author qinkaiyuan
 * @since TODO[起始版本号]
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SocialuniCommentPostQO extends ContentAddQO {
    @NotBlank
    private String talkId;
    private String commentId;
    private String replyCommentId;
}
