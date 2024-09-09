package com.socialuni.social.community.sdk.model.RO.talk;


import com.socialuni.social.common.api.model.SocialuniContentIdRO;
import com.socialuni.social.common.api.model.user.SocialuniUserShowRO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class SocialuniCommentRO extends SocialuniContentIdRO {
    private Integer no;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论时间
     */
    private Date createTime;

    private SocialuniUserShowRO user;

    private List<SocialuniCommentRO> childComments;

    private SocialuniReplyCommentRO replyComment;

    /**
     * 评论数量，子评论数量
     */
    private Integer childCommentNum;
    /**
     * 抱抱次数
     */
    private Integer hugNum;
    private Integer reportNum;
}
