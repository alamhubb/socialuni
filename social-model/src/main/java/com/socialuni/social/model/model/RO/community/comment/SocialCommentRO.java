package com.socialuni.social.model.model.RO.community.comment;


import com.socialuni.social.model.model.RO.user.SocialCommentUserRO;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SocialCommentRO {
    private Integer id;

    private Integer no;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论时间
     */
    private Date createTime;

    private SocialCommentUserRO user;

    private List<SocialCommentRO> childComments;

    private ReplyCommentRO replyComment;

    /**
     * 评论数量，子评论数量
     */
    private Integer childCommentNum;
    /**
     * 抱抱次数
     */
    private Integer hugNum;
}
