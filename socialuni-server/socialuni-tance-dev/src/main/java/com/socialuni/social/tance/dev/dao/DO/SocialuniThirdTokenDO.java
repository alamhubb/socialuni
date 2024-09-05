package com.socialuni.social.tance.dev.dao.DO;

import com.socialuni.social.common.api.entity.SocialuniContentBaseDO;
import com.socialuni.social.common.api.constant.DateTimeType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

//为什么使用s_token
@Entity
@Table(name = "s_uni_user_token",
        //查询条件索引
        indexes = {
                //关联需要键索引，索引列不能为空
                @Index(columnList = "userId"),
                @Index(columnList = "devId"),
        },
        //手机号唯一
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "token"),
                @UniqueConstraint(columnNames = {"devId", "userId"}),
        }
)
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SocialuniThirdTokenDO extends SocialuniContentBaseDO implements Serializable {
    @Column(nullable = false, updatable = false)
    private Long userId;
    @Column(nullable = false, updatable = false)
    private Integer devId;
    @Column(nullable = false, updatable = false)
    private String token;

    public SocialuniThirdTokenDO(Long userId, Integer devId, String token) {
        super(userId);
        this.devId = devId;
        this.token = token;
    }


    //需要有id
    //需要有tokenDO
    //过期时间？没有，过期则重新获取。不考虑

    //本系统登录后，如何登录需要判断是否在 三方中已经注册了。中心不管你注册没注册。只有你自己知道你注册没注册

    //如何从中心获取这个用户的token， 秘钥+id。


    //中心开放一个根据秘钥+id获取token的方法
}


