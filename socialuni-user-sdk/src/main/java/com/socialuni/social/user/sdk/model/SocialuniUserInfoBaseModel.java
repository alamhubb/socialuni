package com.socialuni.social.user.sdk.model;

import com.socialuni.social.common.sdk.model.SocialuniCommonBaseModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class SocialuniUserInfoBaseModel extends SocialuniCommonBaseModel implements Serializable {
    private Integer userId;

    public SocialuniUserInfoBaseModel(Integer userId) {
        this.userId = userId;
    }
}