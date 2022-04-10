package com.socialuni.admin.web.model.RO;

import com.socialuni.admin.web.model.ReportUserVO;
import com.socialuni.social.entity.model.DO.user.SocialUserIdentityAuthDO;
import com.socialuni.social.entity.model.DO.user.SocialUserIdentityAuthImgDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.sdk.config.SocialAppConfig;
import lombok.Data;

@Data
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

    public UserIdentityAuditRO(SocialUserIdentityAuthDO socialUserIdentityAuthDO, SocialUserIdentityAuthImgDO socialUserIdentityAuthImgDO, UserDO userDO) {
        this.checked = false;
        this.id = socialUserIdentityAuthDO.getUserId();
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
