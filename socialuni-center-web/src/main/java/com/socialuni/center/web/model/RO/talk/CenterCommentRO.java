package com.socialuni.center.web.model.RO.talk;


import com.socialuni.center.web.model.RO.user.CenterUserRO;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CenterCommentRO {
    private String id;

    private Integer no;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论时间
     */
    private Date createTime;

    private CenterUserRO user;

    private List<CenterCommentRO> childComments;

    private CenterReplyCommentRO replyComment;

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
