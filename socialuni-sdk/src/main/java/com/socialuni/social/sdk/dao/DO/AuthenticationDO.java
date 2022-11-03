package com.socialuni.social.sdk.dao.DO;

import com.socialuni.social.common.sdk.entity.SocialuniContentBaseDO;
import com.socialuni.social.common.sdk.enumeration.CommonStatus;
import com.socialuni.social.sdk.constant.socialuni.DateTimeType;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2019-02-14 22:03
 */
@Entity
@Table(name = "s_authentication",
        indexes = {
                @Index(columnList = "phoneNum"),
                @Index(columnList = "ip"),
                @Index(columnList = "userId"),
                @Index(columnList = "status")
        }
)
@Data
public class AuthenticationDO extends SocialuniContentBaseDO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //开发者Id，代表那个开发者渠道发送的
    private Integer devId;
    @Column(nullable = false)
    private String phoneNum;
    private String authCode;
    private Date createTime;
    //过期时间
    private Long validTime;
    private Date expiredTime;
    private String ip;
    private Integer userId;
    //校验过，或者已过期，则修改状态
    private String status;

    public AuthenticationDO() {
    }

    public AuthenticationDO(Integer userId, String phoneNum, String authCode, String ip, Integer devId) {
        this.userId = userId;
        this.phoneNum = phoneNum;
        this.authCode = authCode;
        this.devId = devId;
        this.ip = ip;
        Date curDate = new Date();
        this.createTime = curDate;
        this.status = CommonStatus.enable;
        this.validTime = DateTimeType.halfHour;
        this.expiredTime = new Date(curDate.getTime() + this.getValidTime());
    }
}
