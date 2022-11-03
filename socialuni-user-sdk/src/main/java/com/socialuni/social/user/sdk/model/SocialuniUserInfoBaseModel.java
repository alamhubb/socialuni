package com.socialuni.social.user.sdk.model;

import com.socialuni.social.common.dao.DO.SocialCommonBaseDO;
import com.socialuni.social.common.dao.DO.SocialContentBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Data
@NoArgsConstructor
@MappedSuperclass
public class SocialuniUserInfoBaseModel extends SocialCommonBaseDO implements Serializable {
    private Integer userId;
}