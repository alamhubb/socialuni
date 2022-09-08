package com.socialuni.sdk.dao.DO.user;

import com.socialuni.sdk.dao.DO.SocialCommonBaseDO;
import com.socialuni.sdk.dao.DO.SocialContentBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;

//用户扩展类
@Entity
@Table(name = "s_user_expand",
        //查询条件索引
        indexes = {
                //关联需要键索引，索引列不能为空
                @Index(columnList = "schoolName"),
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
}