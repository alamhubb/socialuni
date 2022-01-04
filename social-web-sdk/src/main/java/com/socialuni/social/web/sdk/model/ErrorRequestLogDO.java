package com.socialuni.social.web.sdk.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2019-10-31 13:31
 * 用户详细信息，只有查看用户详情时才需要的信息
 */
@Data
@Entity
@Table(name = "error_request_log", indexes = {
        @Index(columnList = "devId"),
        @Index(columnList = "userId"),
        @Index(columnList = "requestId"),
        @Index(columnList = "ip"),
        @Index(columnList = "requestMethod"),
        @Index(columnList = "uri"),
        @Index(columnList = "spendTime"),
        @Index(columnList = "success"),
        @Index(columnList = "errorCode"),
        @Index(columnList = "errorType"),
})
@NoArgsConstructor
public class ErrorRequestLogDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long requestId;
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

    @Column(columnDefinition = "longtext")
    private String params;
    private String errorMsg;
    @Column(columnDefinition = "longtext")
    private String innerMsg;
    @Column(columnDefinition = "longtext")
    private String innerMsgDetail;
    private Date endTime;
    private Date createTime;

    public ErrorRequestLogDO(RequestLogDO requestLogDO) {
        this.requestId = requestLogDO.getId();
        this.devId = requestLogDO.getDevId();
        this.userId = requestLogDO.getUserId();
        this.ip = requestLogDO.getIp();
        this.uri = requestLogDO.getUri();
        this.spendTime = requestLogDO.getSpendTime();
        this.requestMethod = requestLogDO.getRequestMethod();
        this.success = requestLogDO.getSuccess();
        this.errorCode = requestLogDO.getErrorCode();
        this.errorType = requestLogDO.getErrorType();
        this.params = requestLogDO.getParams();
        this.errorMsg = requestLogDO.getErrorMsg();
        this.innerMsg = requestLogDO.getInnerMsg();
        this.innerMsgDetail = requestLogDO.getInnerMsg();
        this.endTime = requestLogDO.getEndTime();
        this.createTime = requestLogDO.getCreateTime();
    }
}
