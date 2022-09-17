package com.socialuni.sdk.dao.DO.talk;

import com.socialuni.sdk.dao.DO.base.BaseModelDO;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "s_talk_img",
        indexes = {
                @Index(columnList = "talkId")
        }
)
@Data
public class SocialTalkImgDO extends SocialuniImgBaseDO implements BaseModelDO, Serializable {
    @Column(nullable = false, updatable = false)
    private Integer talkId;
}
