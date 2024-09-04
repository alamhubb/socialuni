package com.socialuni.social.common.sdk.dao.DO;


import com.socialuni.social.common.api.entity.SocialuniUserContactBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "s_app_notify")
public class NotifyDO extends SocialuniUserContactBaseDO implements Serializable {
    //加入一列作为外键
    private String contentType;
    private String domainType;
    //加入一列作为外键
    private Long contentId;

    private Boolean hasRead;

    public NotifyDO() {
        this.hasRead = false;
    }

    public NotifyDO(SocialuniUserContactBaseDO socialuniUserContactBaseDO) {
        super(socialuniUserContactBaseDO);
    }
}
