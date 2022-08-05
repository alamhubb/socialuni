package com.socialuni.center.web.model.QO.community.comment;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class SocialCommentDeleteQO {
    @NotNull
    private Integer commentId;

    public SocialCommentDeleteQO(Integer commentId) {
        this.commentId = commentId;
    }
}
