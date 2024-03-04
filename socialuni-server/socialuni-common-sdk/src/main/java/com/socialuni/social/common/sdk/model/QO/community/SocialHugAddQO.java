package com.socialuni.social.common.sdk.model.QO.community;

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
public class SocialHugAddQO {
    @NotNull
    private Integer talkId;
    private Integer commentId;

    public SocialHugAddQO(Integer talkId, Integer commentId) {
        this.talkId = talkId;
        this.commentId = commentId;
    }
}
