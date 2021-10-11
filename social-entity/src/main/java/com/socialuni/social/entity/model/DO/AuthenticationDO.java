package com.socialuni.social.entity.model.DO;

import com.socialuni.social.constant.StatusConst;
import com.socialuni.social.entity.model.DO.user.UserDO;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2019-02-14 22:03
 */
@Entity
@Table(name = "authentication",
        indexes = {
                @Index(columnList = "phoneNum"),
                @Index(columnList = "ip"),
                @Index(columnList = "userId"),
                @Index(columnList = "status")
        }
)
@Data
public class AuthenticationDO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String phoneNum;
    private String authCode;
    private Date createTime;
    private String ip;
    private Integer userId;
    private String status;

    public AuthenticationDO() {
    }

    public AuthenticationDO(Integer userId, String phoneNum, String authCode, String ip) {
        this.userId = userId;
        this.phoneNum = phoneNum;
        this.authCode = authCode;
        this.ip = ip;
        this.createTime = new Date();
        this.status = StatusConst.init;
    }
}
