package com.socialuni.social.user.sdk.model;

import com.socialuni.social.common.dao.DO.SocialContentBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SocialuniUserInfoBaseModel extends SocialContentBaseDO {
    private Integer userId;
}