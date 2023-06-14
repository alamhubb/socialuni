package com.socialuni.social.sdk.model;

import com.socialuni.social.community.sdk.dao.DO.SocialuniCommentDO;
import com.socialuni.social.community.sdk.repository.CommentRepository;
import com.socialuni.social.sdk.dao.utils.content.SocialuniCommentDOUtil;
import com.socialuni.social.sdk.model.RO.talk.SocialuniTalkRO;
import com.socialuni.social.common.api.model.user.SocialuniUserRO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
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
    private CommentRepository commentApi;
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

    private SocialuniTalkRO talk;


    private SocialuniUserRO user;

    private List<UnreadCommentVO> childComments;

    private UnreadCommentVO replyComment;

    public UnreadCommentVO() {
    }

    public UnreadCommentVO(SocialuniCommentDO commentDO) {
        SocialuniUserDo sessionUser = SocialuniUserUtil.getMineUserAllowNull();
//        Integer devId = DevAccountUtils.getDevId();
//
//        this.id = UnionIdDbUtil.createCommentUid(socialCommentDO.getId(),  sessionUser);
        this.no = commentDO.getNo();
        this.content = commentDO.getContent();
        this.createTime = commentDO.getCreateTime();
        this.user = new UserCommentBO(SocialuniUserUtil.getAndCheckUserNotNull(commentDO.getUserId()), sessionUser).toVO();
        //不明白下面这行的意义，未读消息不需要显示子评论吧
        //        this.childComments = UnreadCommentVO.commentDOToVOS(commentRepository.findTop3ByParentCommentOrderByUpdateTimeDescIdDesc(commentDO));
        if (!ObjectUtils.isEmpty(commentDO.getReplyCommentId())) {
            this.replyComment = new UnreadCommentVO(SocialuniCommentDOUtil.getNotCommentNull(commentDO.getReplyCommentId()));
        }
    }

    public static List<UnreadCommentVO> commentDOToVOS(List<?  extends SocialuniCommentDO> commentDOS) {
        return commentDOS.stream().map(UnreadCommentVO::new).collect(Collectors.toList());
    }
}
