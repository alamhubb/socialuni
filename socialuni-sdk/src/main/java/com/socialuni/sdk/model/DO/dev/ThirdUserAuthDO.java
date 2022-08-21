package com.socialuni.sdk.model.DO.dev;

import com.socialuni.social.constant.ConstStatus;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 登录相关，只有登录时才用得到的表
 * 自己表示字段，其他表示关联的表内字段
 */
@Data
@Entity
@Table(name = "s_third_user_auth",
        indexes = {
                @Index(columnList = "status")
        },
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"devId", "userId", "authType"})
        })
public class ThirdUserAuthDO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //商户id
    @Column(nullable = false)
    private Integer devId;
    //允许授权的商户id
    @Column(nullable = false)
    private Integer authDevId;
    //授权的用户id
    @Column(nullable = false)
    private Integer userId;
    //现在拥有的权限类型
    @Column(nullable = false)
    private String authType;

    //三方也要有内容审核功能

    //启用，还是失效
    @Column(nullable = false, columnDefinition = "varchar(20) default '" + ConstStatus.enable + "'")
    private String status;
    //创建时间
    @Column(nullable = false)
    private Date createTime;
    //创建时间
    @Column(nullable = false)
    private Date updateTime;

    public ThirdUserAuthDO() {
    }

    public ThirdUserAuthDO(ThirdUserDO threeUserDO, String authType) {
        //被授权的devId
        this.authDevId = threeUserDO.getAuthDevId();
        this.devId = threeUserDO.getDevId();
        //为三方生成uuid，openid
        //对此授权的devId
        this.userId = threeUserDO.getUserId();
        this.authType = authType;

        this.status = ConstStatus.enable;

        Date curDate = new Date();

        this.createTime = curDate;
        this.updateTime = curDate;
    }
}