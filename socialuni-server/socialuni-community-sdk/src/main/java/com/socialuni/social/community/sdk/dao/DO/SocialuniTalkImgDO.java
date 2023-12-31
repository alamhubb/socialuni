package com.socialuni.social.community.sdk.dao.DO;

import com.socialuni.social.common.api.entity.SocialuniImgBaseDo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "s_community_talk_img",
        indexes = {
                @Index(columnList = "status"),
                @Index(columnList = "userId"),
                @Index(columnList = "updateTime"),
                @Index(columnList = "src"),
                @Index(columnList = "talkId")
        },
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "unionId"),
        }
)
@Data
@EqualsAndHashCode(callSuper = true)
public class SocialuniTalkImgDO extends SocialuniImgBaseDo implements Serializable {
    @Column(nullable = false, updatable = false)
    private Integer talkId;
}
