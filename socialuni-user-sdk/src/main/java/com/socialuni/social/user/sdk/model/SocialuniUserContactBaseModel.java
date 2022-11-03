package com.socialuni.social.user.sdk.model;

import com.socialuni.social.common.sdk.model.SocialuniContentBaseModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class SocialuniUserContactBaseModel extends SocialuniContentBaseModel implements Serializable {
    private Integer beUserId;
}