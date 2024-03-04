package com.socialuni.social.community.sdk.dao.DO;

import com.socialuni.social.common.api.entity.SocialuniBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 群聊会话关联表
 *
 * @author qinkaiyuan
 * @date 2022-12-04 03:03
 */
//                @Index(columnList = "showFront"),
@Entity
@Table(name = "s_community_circle_chat",
        indexes = {
                @Index(columnList = "circleName"),
                @Index(columnList = "devId")
        },
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"circleName", "devId"})
        }
)
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SocialuniCircleChatDO extends SocialuniBaseDO {
    @Column(nullable = false)
    private String circleName;
    @Column(nullable = false)
    //群聊会话id
    private String groupChatId;
    @Column(nullable = false)
    private Integer devId;
}
