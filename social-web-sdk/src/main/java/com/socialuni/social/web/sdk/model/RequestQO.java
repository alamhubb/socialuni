package com.socialuni.social.web.sdk.model;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2019-10-31 13:31
 * 用户详细信息，只有查看用户详情时才需要的信息
 */
@Data
public class RequestQO {
    //邀请你的用户
    private Integer devId;
    private Integer requestId;
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


    public RequestQO() {
    }


}
