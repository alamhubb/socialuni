package com.socialuni.api.model.QO.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CenterUserImgDeleteQO {
    @NotBlank
    private String userImgId;

    public CenterUserImgDeleteQO(String userImgId) {
        this.userImgId = userImgId;
    }
}
