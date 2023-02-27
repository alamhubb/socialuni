package com.socialuni.social.tance.model.DO;

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
 * @deprecated  表设计冗余。
 */
@Table(name = "s_app_config")
@IdClass(value = AppConfigPk.class)
public class AppConfigDO implements Serializable {
    @Id
    private Integer devId;
    /**
     * key值
     */
    @Id
    private String configKey;
    private String value;
    private String label;
    private int status;
    @Column(nullable = false, updatable = false, columnDefinition = "timestamp default current_timestamp")
    private Date createTime;

    public AppConfigDO() {
        this.status = 1;
    }
}
