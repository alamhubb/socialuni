package com.socialuni.social.user.sdk.model;

import com.socialuni.social.common.dao.DO.SocialCommonBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@MappedSuperclass
@Data
@NoArgsConstructor
public class SocialuniUserInfoBaseDO extends SocialCommonBaseDO {
    private Integer userId;
}