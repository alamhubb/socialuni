package com.socialuni.social.community.sdk.model;

import com.socialuni.social.user.sdk.api.SocialuniTextCheckDO;
import com.socialuni.social.user.sdk.model.SocialuniContentBaseModel;
import lombok.Data;

import java.io.Serializable;

@Data
public class SocialuniCommentModel extends SocialuniContentBaseModel implements SocialuniTextCheckDO, Serializable {
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
     * 需要发送的通知
     */
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
    private Boolean hasUnderageContent;
    private Boolean hasContactInfo;
}
