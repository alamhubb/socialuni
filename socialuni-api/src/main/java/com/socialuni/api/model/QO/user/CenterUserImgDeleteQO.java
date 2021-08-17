package com.socialuni.api.model.QO.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CenterUserImgDeleteQO {
    @NotBlank
    private String userImgId;
}
