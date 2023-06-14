package com.socialuni.social.admin.model.RO;

import com.socialuni.social.admin.model.ReportUserVO;
import com.socialuni.social.user.sdk.model.DO.SocialUserIdentityAuthDO;
import com.socialuni.social.user.sdk.model.DO.SocialUserIdentityAuthImgDO;
import com.socialuni.social.tance.sdk.enumeration.SocialuniSystemConst;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
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

    public UserIdentityAuditRO(SocialUserIdentityAuthDO socialUserIdentityAuthDO, SocialUserIdentityAuthImgDO socialUserIdentityAuthImgDO, SocialuniUserDo userDO) {
        this.checked = true;
        this.success = true;
        this.id = socialUserIdentityAuthDO.getId();
        this.user = new ReportUserVO(userDO);
        this.userIdImgSrc = SocialuniSystemConst.getStaticResourceUrl() + socialUserIdentityAuthImgDO.getUserIdImgSrc();
        this.userSelfieImgSrc = SocialuniSystemConst.getStaticResourceUrl() + socialUserIdentityAuthImgDO.getUserSelfieImgSrc();
        this.preCheckScore = socialUserIdentityAuthImgDO.getPreCheckScore();
        this.age = socialUserIdentityAuthDO.getAge();
        this.birth = socialUserIdentityAuthDO.getBirth();
        this.name = socialUserIdentityAuthDO.getName();
        this.nation = socialUserIdentityAuthDO.getNation();
        this.sex = socialUserIdentityAuthDO.getSex();
        this.authNum = socialUserIdentityAuthDO.getAuthNum();
    }
}
