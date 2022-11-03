package com.socialuni.social.user.sdk.model;

import com.socialuni.social.common.dao.DO.SocialUnionContentBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class SocialuniUserModel extends SocialUnionContentBaseDO implements Serializable {
    private String nickname;
    private String avatar;
    private String gender;
    private String birthday;
    private Integer age;
    private String city;
    private String type;
}