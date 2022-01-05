package com.socialuni.social.web.sdk.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2019-10-31 13:31
 * 用户详细信息，只有查看用户详情时才需要的信息
 */
@Data
@Entity
@Table(name = "s_request_log", indexes = {
        @Index(columnList = "devId"),
        @Index(columnList = "userId"),
        @Index(columnList = "ip"),
        @Index(columnList = "requestMethod"),
        @Index(columnList = "uri"),
        @Index(columnList = "spendTime"),
        @Index(columnList = "success"),
        @Index(columnList = "errorCode"),
        @Index(columnList = "errorType"),
})
public class RequestLogDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //邀请你的用户
    private Integer devId;
    private Integer userId;
    private String ip;
    private String uri;
    private Long spendTime;
    private String requestMethod;
    private Boolean success;
    private Integer errorCode;
    private String errorType;
    private String provider;
    private String platform;
    private String systemInfo;

    @Column(columnDefinition = "longtext")
    private String params;
    private String errorMsg;
    @Column(columnDefinition = "longtext")
    private String innerMsg;
    @Column(columnDefinition = "longtext")
    private String innerMsgDetail;
    private Date endTime;
    private Date createTime;
}
