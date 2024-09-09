package com.socialuni.social.user.sdk.dao.DO;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//用户扩展类
@Entity
@Table(name = "s_user_open_id",
        //查询条件索引
        indexes = {
                //关联需要键索引，索引列不能为空
                @Index(columnList = "userOpenId"),
        },
        uniqueConstraints = {
                //每个渠道都是唯一的
                @UniqueConstraint(columnNames = "userId"),
        })
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SocialuniUserOpenIdDO extends SocialuniUserInfoBaseDO {
    private String userOpenId;
}