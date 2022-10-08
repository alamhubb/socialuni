package com.socialuni.social.user.sdk.model;


import com.socialuni.social.common.dao.DO.SocialContentBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

//用户扩展类
@Entity
@Table(name = "s_user_expand",
        //查询条件索引
        indexes = {
                //关联需要键索引，索引列不能为空
                @Index(columnList = "schoolName"),
                @Index(columnList = "openContactInfo"),
        },
        uniqueConstraints = {
                //每个渠道都是唯一的
                @UniqueConstraint(columnNames = "userId"),
        })
@Data
@NoArgsConstructor
public class SocialuniUserExpandDO extends SocialContentBaseDO implements Serializable {
    private Integer userId;
    private String schoolName;
    private String contactInfo;

    //开启了可获取联系方式
    @Column(nullable = false)
    private Boolean openContactInfo;
}