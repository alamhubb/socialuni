package com.socialuni.center.web.model.DO;

import com.socialuni.center.web.utils.UnionIdDbUtil;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 登录相关，只有登录时才用得到的表
 * 自己表示字段，其他表示关联的表内字段
 */
@Data
@Table(name = "third_user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"devId", "userId"})
        })
@Entity
public class ThirdUserDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //商户id
    @Column(nullable = false)
    private Integer devId;
    //允许授权的商户id
    @Column(nullable = false)
    private Integer authDevId;
    @Column(nullable = false)
    private Integer userId;
    //三方userid
    @Column(nullable = false)
    private String thirdUserId;
    //创建时间
    @Column(nullable = false)
    private Date createTime;
    //创建时间
    @Column(nullable = false)
    private Date updateTime;

    public ThirdUserDO() {
    }

    public ThirdUserDO(Integer devId, Integer userId) {
        //三方商户id
        this.devId = devId;
        this.authDevId = 1;
        //为三方生成uuid，openid
        this.thirdUserId = UnionIdDbUtil.createUserUid(userId, userId, devId);
        //对此授权的devId
        this.userId = userId;
        Date curDate = new Date();

        this.createTime = curDate;
        this.updateTime = curDate;
    }
}