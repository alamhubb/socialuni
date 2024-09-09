package com.socialuni.social.expand.dao.DO;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

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
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class SocialuniUserExpandDo extends SocialuniContentBaseDO {
    private Long userId;
    private String schoolName;
    private String contactInfo;
    private Date lastOnlineTime;

    //开启了可获取联系方式
    @Column(nullable = false)
    private Boolean openContactInfo;
}