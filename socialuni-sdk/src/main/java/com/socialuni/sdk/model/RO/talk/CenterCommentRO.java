package com.socialuni.sdk.model.RO.talk;


import com.socialuni.sdk.factory.RO.talk.CenterCommentROFactory;
import com.socialuni.sdk.model.RO.community.UniContentIdRO;
import com.socialuni.sdk.model.RO.user.CenterUserRO;
import com.socialuni.sdk.constant.socialuni.ContentType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class CenterCommentRO extends UniContentIdRO {
    private CenterUserRO user;
    private List<CenterCommentRO> childComments;
    private CenterReplyCommentRO replyComment;


    private Integer no;
    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论时间
     */
    private Date createTime;
    /**
     * 评论数量，子评论数量
     */
    private Integer childCommentNum;
    /**
     * 抱抱次数
     */
    private Integer hugNum;
    private Integer reportNum;

    public CenterCommentRO(CenterCommentRO centerCommentRO) {
        super(ContentType.comment, centerCommentRO);
        this.user = new CenterUserRO(centerCommentRO.getUser());
        this.childComments = CenterCommentROFactory.getCommentROs(centerCommentRO.getChildComments());
        if (centerCommentRO.getReplyComment() != null) {
            this.replyComment = new CenterReplyCommentRO(centerCommentRO.getReplyComment());
        }
        this.no = centerCommentRO.getNo();
        this.content = centerCommentRO.getContent();
        this.createTime = centerCommentRO.getCreateTime();
        this.childCommentNum = centerCommentRO.getChildCommentNum();
        this.hugNum = centerCommentRO.getHugNum();
        this.reportNum = centerCommentRO.getReportNum();
    }
}
