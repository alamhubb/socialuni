package com.socialuni.social.common.sdk.dao.DO;

import com.socialuni.social.common.api.entity.SocialuniBaseDO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2019-10-31 13:31
 * 用户详细信息，只有查看用户详情时才需要的信息
 */
@Data
@Entity
@Table(name = "s_app_jpa_sql_log", indexes = {
        @Index(columnList = "requestId"),
        @Index(columnList = "interfaceMethod"),
        @Index(columnList = "spendTime"),
})
public class JpaSqlLogDO extends SocialuniBaseDO implements Serializable {
    //邀请你的用户
    private Long requestId;
    private String interfaceMethod;
    private Long spendTime;

    @Column(columnDefinition = "longtext")
    private String params;
    private Date endTime;
}
