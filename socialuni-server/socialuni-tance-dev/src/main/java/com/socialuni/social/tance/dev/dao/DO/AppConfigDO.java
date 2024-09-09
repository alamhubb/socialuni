package com.socialuni.social.tance.dev.dao.DO;

import com.qingchi.qing.jpa.dao.model.QingJpaBaseDO;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2019-12-22 18:46
 */
@Data
@Entity
/**
 * 全局配置表
 * @deprecated 表设计冗余。
 */
@Table(name = "s_app_config",
        indexes = {
                @Index(columnList = "devId"),
                @Index(columnList = "status"),
                @Index(columnList = "configKey"),
                @Index(columnList = "createTime")
        },
        uniqueConstraints = {
                //每个渠道都是唯一的
                @UniqueConstraint(columnNames = {"devId", "configKey"})
        }
)
//@IdClass(value = AppConfigPk.class)
public class AppConfigDO extends QingJpaBaseDO implements Serializable {
    @Column(nullable = false)
    private Integer devId;
    /**
     * key值
     */
    @Column(nullable = false)
    private String configKey;
    private String value;
    private String label;
    private int status;
    @Column(nullable = false, updatable = false, columnDefinition = "timestamp default current_timestamp")
    private Date createTime;

    public AppConfigDO() {
        this.status = 1;
    }

    public AppConfigDO(Integer devId, String configKey, String value) {
        this();
        this.devId = devId;
        this.configKey = configKey;
        this.value = value;
    }
}
