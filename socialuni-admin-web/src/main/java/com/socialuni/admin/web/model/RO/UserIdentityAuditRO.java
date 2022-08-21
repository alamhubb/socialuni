package com.socialuni.admin.web.model.RO;

import com.socialuni.admin.web.model.ReportUserVO;
import com.socialuni.sdk.model.DO.user.SocialUserIdentityAuthDO;
import com.socialuni.sdk.model.DO.user.SocialUserIdentityAuthImgDO;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.config.SocialAppConfig;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserIdentityAuditRO {
    Boolean checked;
    Integer id;

    ReportUserVO user;
    String userIdImgSrc;
    String userSelfieImgSrc;
    Integer preCheckScore;

    Integer age;
    String birth;
    String name;
    String nation;
    String sex;
    //认证次数
    Integer authNum;
    Boolean success;

    public UserIdentityAuditRO(SocialUserIdentityAuthDO socialUserIdentityAuthDO, SocialUserIdentityAuthImgDO socialUserIdentityAuthImgDO, SocialUserDO userDO) {
        this.checked = true;
        this.success = true;
        this.id = socialUserIdentityAuthDO.getId();
        this.user = new ReportUserVO(userDO);
        this.userIdImgSrc = SocialAppConfig.getStaticResourceUrl() + socialUserIdentityAuthImgDO.getUserIdImgSrc();
        this.userSelfieImgSrc = SocialAppConfig.getStaticResourceUrl() + socialUserIdentityAuthImgDO.getUserSelfieImgSrc();
        this.preCheckScore = socialUserIdentityAuthImgDO.getPreCheckScore();
        this.age = socialUserIdentityAuthDO.getAge();
        this.birth = socialUserIdentityAuthDO.getBirth();
        this.name = socialUserIdentityAuthDO.getName();
        this.nation = socialUserIdentityAuthDO.getNation();
        this.sex = socialUserIdentityAuthDO.getSex();
        this.authNum = socialUserIdentityAuthDO.getAuthNum();
    }
}
