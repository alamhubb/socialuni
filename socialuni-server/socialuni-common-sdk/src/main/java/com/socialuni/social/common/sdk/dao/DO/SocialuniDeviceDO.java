package com.socialuni.social.common.sdk.dao.DO;

import com.qingchi.qing.jpa.dao.model.QingJpaBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "s_app_device",
        indexes = {
                @Index(columnList = "device"),
                @Index(columnList = "deviceOther"),
                @Index(columnList = "uuid"),
        }
)
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SocialuniDeviceDO extends QingJpaBaseDO {
    private String device;
    private String deviceOther;
    private String uuid;
}