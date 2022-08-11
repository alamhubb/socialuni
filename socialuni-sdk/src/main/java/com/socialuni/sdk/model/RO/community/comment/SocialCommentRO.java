package com.socialuni.sdk.model.RO.community.comment;


import com.socialuni.sdk.model.RO.community.SocialContentIdRO;
import com.socialuni.sdk.model.RO.user.base.SocialUserRO;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SocialCommentRO extends SocialContentIdRO {
    private Integer no;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论时间
     */
    private Date createTime;

    private SocialUserRO user;

    private List<SocialCommentRO> childComments;

    private SocialReplyCommentRO replyComment;

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