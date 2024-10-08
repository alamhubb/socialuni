package com.socialuni.social.community.sdk.dao.DO;

import com.socialuni.social.common.api.entity.SocialuniUnionContentBaseDO;
import com.socialuni.social.content.dao.DO.SocialuniTextCheckDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "s_community_comment",
        indexes = {
                @Index(columnList = "status"),
                @Index(columnList = "userId"),
                @Index(columnList = "updateTime"),
                @Index(columnList = "createTime"),
                @Index(columnList = "talkId"),
                @Index(columnList = "parentCommentId"),
                @Index(columnList = "hasUnderageContent"),
                @Index(columnList = "hasContactInfo")
        },
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "unionId"),
        }
)
@Data
@EqualsAndHashCode(callSuper = true)
public class SocialuniCommentDO extends SocialuniUnionContentBaseDO implements SocialuniTextCheckDO, Serializable {
    private Integer no;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论的说说
     */
    private Long talkId;

    /**
     * 2级评论
     */
    private Long parentCommentId;

    /**
     * 回复的哪个评论
     */
    private Long replyCommentId;

    /**
     * 需要发送的通知
     */
    @Transient
//    private List<NotifyDO> notifies;

    /**
     * 评论数量，子评论数量
     */
    private Integer childCommentNum;
    /**
     * 抱抱次数
     */
    private Integer hugNum;

    //最大最小可见年龄
    private Integer minAge;
    private Integer maxAge;
    //可见性别
    private String gender;
    //三方id，只有为三方自己查询的时候才显示
    private String threeId;

    //ocr后查看是否包含未成年内容
    @Column(nullable = false)
    private Boolean hasUnderageContent;
    @Column(nullable = false)
    private Boolean hasContactInfo;
}
