package com.socialuni.social.userImg.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class SocialUserImgDeleteQO {
    @NotNull
    private Long userImgId;

    public SocialUserImgDeleteQO(Long userImgId) {
        this.userImgId = userImgId;
    }
}
