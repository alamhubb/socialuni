package com.socialuni.social.music.sdk.model.QO;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SocialuniPlayMusicQO {
    @NotBlank
    String musicId;
    @NotBlank
    String musicToken;
}
