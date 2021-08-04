package com.socialuni.social.model.model.QO.community.comment;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SocialCommentDeleteQO {
    @NotNull
    private Integer commentId;
}
