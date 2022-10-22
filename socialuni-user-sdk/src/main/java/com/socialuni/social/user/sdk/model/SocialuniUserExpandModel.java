package com.socialuni.social.user.sdk.model;


import com.socialuni.social.common.dao.DO.SocialContentBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class SocialuniUserExpandModel extends SocialContentBaseDO implements Serializable {
    private Integer userId;
    private String schoolName;
    private String contactInfo;

    //开启了可获取联系方式
    private Boolean openContactInfo;
}