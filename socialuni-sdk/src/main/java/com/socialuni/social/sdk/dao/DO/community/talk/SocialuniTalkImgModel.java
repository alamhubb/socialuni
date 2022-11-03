package com.socialuni.social.sdk.dao.DO.community.talk;

import com.socialuni.social.common.sdk.entity.SocialuniImgBaseDo;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "s_talk_img",
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
public class SocialuniTalkImgModel extends SocialuniImgBaseDo implements Serializable {
    @Column(nullable = false, updatable = false)
    private Integer talkId;
}
