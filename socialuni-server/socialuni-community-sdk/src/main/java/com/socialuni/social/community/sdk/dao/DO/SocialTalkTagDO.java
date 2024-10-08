package com.socialuni.social.community.sdk.dao.DO;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author qinkaiyuan
 * @date 2019-11-07 15:20
 */
@Entity
@Table(name = "s_community_talk_tag",
        indexes = {
                @Index(columnList = "talkId"),
                @Index(columnList = "tagId")
        },
        uniqueConstraints = {
                //一个人只能关注另一个人一次
                @UniqueConstraint(columnNames = {"talkId", "tagId"})
        }
)
@Data
public class SocialTalkTagDO implements Serializable {
    //此类为talk子类，只能包含基础数据类型
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Long talkId;
    private Integer tagId;
}
