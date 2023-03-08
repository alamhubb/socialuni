package com.socialuni.social.user.sdk.model.QO.user;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SocialuniUserExtendFriendQueryQO {
    @NotBlank
    String pageType;
    String adCode;
    String lon;
    String lat;
}