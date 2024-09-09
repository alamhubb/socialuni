package com.socialuni.social.user.sdk.dao.DO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//用户扩列好友表
@Entity
@Table(name = "s_user_hug",
        indexes = {
                @Index(columnList = "hugNum"),
        },
        uniqueConstraints = {
                //每个渠道都是唯一的
                @UniqueConstraint(columnNames = "userId"),
        })
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class SocialuniUserHugDo extends SocialuniContentBaseDO {
        @Column(nullable = false, columnDefinition = "int default 0")
        private Integer hugNum;
}