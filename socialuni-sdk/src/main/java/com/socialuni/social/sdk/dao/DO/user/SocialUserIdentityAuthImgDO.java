package com.socialuni.social.sdk.dao.DO.user;

import com.socialuni.social.sdk.dao.DO.base.CommonContentBaseDO;
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
public class SocialUserIdentityAuthImgDO extends CommonContentBaseDO {
    private Integer userId;
    private String userIdImgSrc;
    private String userSelfieImgSrc;
    private Integer preCheckScore;
}
