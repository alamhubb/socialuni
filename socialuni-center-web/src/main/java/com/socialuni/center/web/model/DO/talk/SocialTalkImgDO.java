package com.socialuni.center.web.model.DO.talk;

import com.socialuni.center.web.model.DO.base.SocialImgBaseDO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;

@Entity
@Table(name = "talk_img",
        indexes = {
                @Index(columnList = "contentId")
        },
        uniqueConstraints = {
                //一个人只能关注另一个人一次
                @UniqueConstraint(columnNames = {"src"})
        }
)
@Data
public class SocialTalkImgDO extends SocialImgBaseDO implements Serializable {

}
