package com.socialuni.sdk.model.DO;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2019-12-22 18:46
 */
@Data
@Entity
/**
 * 全局配置表
 */
@Table(name = "app_config")
public class AppConfigDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String configKey;
    private String valueType;
    private Integer numberValue;
    private String stringValue;
    private Date dateValue;
    private Boolean booleanValue;
    private String annotation;
    private String status;
    private Date createTime;
    private Date updateTime;
}
