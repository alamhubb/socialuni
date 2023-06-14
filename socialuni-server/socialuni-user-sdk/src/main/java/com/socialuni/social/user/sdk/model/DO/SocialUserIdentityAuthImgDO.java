package com.socialuni.social.user.sdk.model.DO;

import com.socialuni.social.common.api.entity.SocialuniContentBaseDO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "s_user_identity_auth_img", indexes = {
        //关联需要键索引，索引列不能为空
        @Index(columnList = "userId"),
})
public class SocialUserIdentityAuthImgDO extends SocialuniContentBaseDO {
    private Integer userId;
    private String userIdImgSrc;
    private String userSelfieImgSrc;
    private Integer preCheckScore;
}
