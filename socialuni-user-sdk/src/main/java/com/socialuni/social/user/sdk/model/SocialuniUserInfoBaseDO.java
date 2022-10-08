package com.socialuni.social.user.sdk.model;

import com.socialuni.social.common.dao.DO.SocialContentBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
@NoArgsConstructor
public class SocialuniUserInfoBaseDO extends SocialContentBaseDO {
    private Integer userId;
}