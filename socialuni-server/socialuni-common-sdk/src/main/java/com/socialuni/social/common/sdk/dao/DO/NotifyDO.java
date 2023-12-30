package com.socialuni.social.common.sdk.dao.DO;


import com.socialuni.social.common.api.entity.SocialuniUserContactBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "s_app_notify")
@NoArgsConstructor
public class NotifyDO extends SocialuniUserContactBaseDO implements Serializable {
    //加入一列作为外键
    private String contentType;

    //加入一列作为外键
    private Integer contentId;

    private Boolean hasRead;

    public NotifyDO(SocialuniUserContactBaseDO socialuniUserContactBaseDO) {
        super(socialuniUserContactBaseDO);
    }
}
