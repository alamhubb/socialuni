package com.socialuni.social.user.sdk.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class SocialuniUserContactBaseModel extends SocialuniContentBaseModel implements Serializable {
    private Integer beUserId;
}