package com.socialuni.social.tance.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

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
    private String devKey;
    /**
     * key值
     */
    @Id
    private String key;
    private String value;
    private String desc;
    private int status;
}
