package com.socialuni.sdk.dao.DO.base;

import com.socialuni.sdk.dao.DO.SocialCommonBaseDO;
import com.socialuni.sdk.dao.DO.user.SocialUnionContentBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Data
@NoArgsConstructor
public class SocialuniUserInfoBaseDO extends SocialCommonBaseDO {
    private Integer userId;
}