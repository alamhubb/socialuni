package com.socialuni.social.common.sdk.model.QO.comment;


import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SocialuniCommentDeleteQO {
    @NotBlank
    private String commentId;
}
