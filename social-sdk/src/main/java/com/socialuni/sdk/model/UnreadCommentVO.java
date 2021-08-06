package com.socialuni.sdk.model;

import com.socialuni.social.model.model.RO.community.talk.SocialTalkRO;
import com.socialuni.sdk.model.DO.comment.CommentDO;
import com.socialuni.sdk.repository.CommentRepository;
import com.socialuni.sdk.utils.CommentUtils;
import com.socialuni.sdk.model.DO.user.UserDO;
import com.socialuni.sdk.utils.SocialUserUtil;
import com.socialuni.social.model.model.RO.user.base.SocialUserRO;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Component
public class UnreadCommentVO {
    @Resource
    private CommentRepository commentRepository;
    //如果这个评论 有parent，就代表已经是一个子评论，就不用把他设置为parent而是用它的parentId，他是否有parent

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

    private SocialTalkRO talk;


    private SocialUserRO user;

    private List<UnreadCommentVO> childComments;

    private UnreadCommentVO replyComment;

    public UnreadCommentVO() {
    }

    public UnreadCommentVO(CommentDO commentDO) {
        UserDO sessionUser = SocialUserUtil.getMineUser();
//        Integer devId = DevAccountUtils.getDevId();
//
//        this.id = UnionIdDbUtil.createCommentUid(socialCommentDO.getId(),  sessionUser);
        this.no = commentDO.getNo();
        this.content = commentDO.getContent();
        this.createTime = commentDO.getCreateTime();
        this.user = new UserCommentBO(SocialUserUtil.get(commentDO.getUserId()),  sessionUser).toVO();
        //不明白下面这行的意义，未读消息不需要显示子评论吧
        //        this.childComments = UnreadCommentVO.commentDOToVOS(commentRepository.findTop3ByParentCommentOrderByUpdateTimeDescIdDesc(commentDO));
        if (!ObjectUtils.isEmpty(commentDO.getReplyCommentId())) {
            this.replyComment = new UnreadCommentVO(CommentUtils.get(commentDO.getReplyCommentId()));
        }
    }

    public static List<UnreadCommentVO> commentDOToVOS(List<CommentDO> commentDOS) {
        return commentDOS.stream().map(UnreadCommentVO::new).collect(Collectors.toList());
    }
}
