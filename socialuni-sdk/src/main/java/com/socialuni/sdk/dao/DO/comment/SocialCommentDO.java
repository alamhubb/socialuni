package com.socialuni.sdk.dao.DO.comment;

import com.socialuni.sdk.dao.DO.base.BaseModelDO;
import com.socialuni.sdk.dao.DO.user.SocialUnionContentBaseDO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

@Entity
@Table(name = "s_comment", indexes = {
        @Index(columnList = "status"),
        @Index(columnList = "createTime"),
        @Index(columnList = "updateTime"),
        @Index(columnList = "userId"),
        @Index(columnList = "talkId"),
        @Index(columnList = "parentCommentId"),
        @Index(columnList = "content"),
        @Index(columnList = "contentType"),
        @Index(columnList = "unionId"),
})
@Data
public class SocialCommentDO extends SocialUnionContentBaseDO implements BaseModelDO, Serializable {
    private String socialuniUid;
    private Integer no;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论的说说
     */
    private Integer talkId;

    /**
     * 2级评论
     */
    private Integer parentCommentId;

    /**
     * 回复的哪个评论
     */
    private Integer replyCommentId;

    /**
     * 哪个评论的
     */
    private Integer userId;

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
    /**
     * 举报次数
     */
    private Integer reportNum;

    /**
     * 删除原因
     */
    private String deleteReason;
    private String violateType;
    private String contentType;

    //最大最小可见年龄
    private Integer minAge;
    private Integer maxAge;
    //可见性别
    private String gender;
    //三方id，只有为三方自己查询的时候才显示
    private String threeId;
}
