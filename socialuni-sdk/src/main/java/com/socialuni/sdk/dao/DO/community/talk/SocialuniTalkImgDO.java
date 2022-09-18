package com.socialuni.sdk.dao.DO.community.talk;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "s_talk_img",
        indexes = {
                @Index(columnList = "talkId")
        }
)
@Data
public class SocialuniTalkImgDO extends SocialuniImgBaseDO implements Serializable {
    @Column(nullable = false, updatable = false)
    private Integer talkId;
}
