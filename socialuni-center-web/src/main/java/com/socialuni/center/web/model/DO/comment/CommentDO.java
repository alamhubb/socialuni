package com.socialuni.center.web.model.DO.comment;

import com.socialuni.center.web.model.DO.base.CommonContentBaseDO;
import com.socialuni.center.web.model.DO.base.BaseModelDO;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "comment", indexes = {
        @Index(columnList = "status"),
        @Index(columnList = "createTime"),
        @Index(columnList = "updateTime"),
        @Index(columnList = "userId"),
        @Index(columnList = "talkId"),
        @Index(columnList = "parentCommentId"),
        @Index(columnList = "content"),
        @Index(columnList = "reportContentType"),
})
@Data
public class CommentDO extends CommonContentBaseDO implements BaseModelDO, Serializable {
    //如果这个评论 有parent，就代表已经是一个子评论，就不用把他设置为parent而是用它的parentId，他是否有parent
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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
    private String reportContentType;

    //最大最小可见年龄
    private Integer minAge;
    private Integer maxAge;
    //可见性别
    private String gender;
    //发表商户
    private Integer devId;
    //三方id，只有为三方自己查询的时候才显示
    private String threeId;
}
