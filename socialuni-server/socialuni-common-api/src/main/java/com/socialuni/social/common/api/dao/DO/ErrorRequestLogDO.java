package com.socialuni.social.common.api.dao.DO;

import com.socialuni.social.common.api.constant.ErrorPlatformType;
import com.socialuni.social.common.api.dao.DO.RequestLogDO;
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
@Table(name = "s_app_request_error_log", indexes = {
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
        @Index(columnList = "errorPlatform"),
        @Index(columnList = "provider"),
        @Index(columnList = "platform"),
        @Index(columnList = "systemInfo"),
})
@NoArgsConstructor
public class ErrorRequestLogDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long requestId;
    //邀请你的用户
    private Integer devId;
    private Long userId;
    private String ip;
    private String uri;
    private String frontPage;
    private Long spendTime;
    private String requestMethod;
    private Boolean success;
    private Integer errorCode;
    private String errorType;
    //前台还是后台错误，ErrorPlatformType
    private String errorPlatform;
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
        this.systemInfo = requestLogDO.getSystemInfo();
        this.provider = requestLogDO.getProvider();
        this.platform = requestLogDO.getPlatform();
        this.errorPlatform = ErrorPlatformType.serverError;
    }
}
