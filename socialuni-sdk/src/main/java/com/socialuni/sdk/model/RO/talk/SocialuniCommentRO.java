package com.socialuni.sdk.model.RO.talk;


import com.socialuni.sdk.model.RO.user.SocialuniContentIdRO;
import com.socialuni.sdk.model.RO.user.SocialuniUserRO;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
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

    private SocialuniUserRO user;

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
