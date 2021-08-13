package com.socialuni.social.entity.model.DO.talk;

import com.socialuni.social.entity.model.DO.CommonBaseDO;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "talk_img",
        indexes = {
                @Index(columnList = "talkId")
        },
        uniqueConstraints = {
                //一个人只能关注另一个人一次
                @UniqueConstraint(columnNames = {"src"})
        }
)
@Data
//
public class SocialTalkImgDO extends CommonBaseDO {
    //此类为talk子类，只能包含基础数据类型
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer talkId;

    private String src;

    private Double aspectRatio;

    //压缩率
    private Double quality;

    private Integer size;
}
