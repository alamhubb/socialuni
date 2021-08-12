package com.socialuni.sdk.model;

import com.socialuni.entity.model.DO.CommonBaseDO;
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
@Table(name = "operate_log", indexes = {
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
public class OperateLogDO extends CommonBaseDO {
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

    @Column(columnDefinition = "text")
    private String params;
    private String errorMsg;
    private String innerMsg;
    @Column(columnDefinition = "text")
    private String innerMsgDetail;
    private Date endTime;


    public OperateLogDO() {
    }


}
