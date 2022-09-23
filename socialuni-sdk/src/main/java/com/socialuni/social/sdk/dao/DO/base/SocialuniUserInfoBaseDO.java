package com.socialuni.social.sdk.dao.DO.base;

import com.socialuni.social.sdk.dao.DO.SocialCommonBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@MappedSuperclass
@Data
@NoArgsConstructor
public class SocialuniUserInfoBaseDO extends SocialCommonBaseDO {
    private Integer userId;
}