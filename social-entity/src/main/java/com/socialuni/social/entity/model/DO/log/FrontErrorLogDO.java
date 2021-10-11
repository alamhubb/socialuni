package com.socialuni.social.entity.model.DO.log;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:48
 */
//记录前端错误日志，不该出现的错误@Entity
@Entity
@Table(name = "front_error_log")
@Data
public class FrontErrorLogDO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createTime;

    private Integer userId;

    //出错的url
    private String uri;

    //出错的业务备注
    @Column(columnDefinition = "text")
    private String detail;

    //传入了什么参数导致报错
    @Column(columnDefinition = "text")
    private String params;

    //错误信息
    @Column(columnDefinition = "text")
    private String errorMsg;

    private String platform;
    private String provider;
    private String appVersion;
    private Integer devId;
}
