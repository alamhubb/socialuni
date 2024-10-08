package com.socialuni.social.identity.dao.DO;

import com.socialuni.social.common.api.entity.SocialuniContentBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "s_user_identity_auth_img", indexes = {
        //关联需要键索引，索引列不能为空
        @Index(columnList = "userId"),
})
@EqualsAndHashCode(callSuper = true)
public class SocialUserIdentityAuthImgDO extends SocialuniContentBaseDO {
    private Long userId;
    private String userIdImgSrc;
    private String userSelfieImgSrc;
    private Integer preCheckScore;
}
