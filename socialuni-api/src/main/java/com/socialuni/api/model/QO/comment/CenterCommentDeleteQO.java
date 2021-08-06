package com.socialuni.api.model.QO.comment;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CenterCommentDeleteQO {
    @NotBlank
    private String commentId;
}
