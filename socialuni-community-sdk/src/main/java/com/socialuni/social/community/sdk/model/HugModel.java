package com.socialuni.social.community.sdk.model;

import com.socialuni.social.common.sdk.entity.SocialuniContentBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class HugModel extends SocialuniContentBaseDO implements Serializable {
    /**
     * 哪个评论的
     */
    private Integer userId;
    /**
     * 赞的说说
     */
    private Integer talkId;

    /**
     * 赞的说说
     */
    private Integer commentId;

    public HugModel(Integer userId, Integer talkId, Integer commentId) {
        this.userId = userId;
        this.talkId = talkId;
        this.commentId = commentId;
    }
}
