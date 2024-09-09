package com.socialuni.social.likee.dao.DO;

import com.qingchi.qing.jpa.SocialuniUserInfoBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//用户扩展类
@Entity
@Table(name = "s_user_like_chat",
        //查询条件索引
        indexes = {
                //关联需要键索引，索引列不能为空
                @Index(columnList = "userId"),
                @Index(columnList = "createTime"),
        },
        uniqueConstraints = {
                //每个渠道都是唯一的
                @UniqueConstraint(columnNames = {"chatId"}),
        })
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class SocialuniUserLikeChatDO extends SocialuniUserInfoBaseDO {
    @Column(updatable = false, nullable = false)
    private Long chatId;
}