package com.socialuni.social.user.sdk.model;

import com.socialuni.social.common.sdk.entity.SocialuniUnionContentBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class SocialuniUserModel extends SocialuniUnionContentBaseDO implements Serializable {
    private String nickname;
    private String avatar;
    private String gender;
    private String birthday;
    private Integer age;
    private String city;
    private String type;
}