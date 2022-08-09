package com.socialuni.center.web.entity.comment;

import com.socialuni.center.web.manage.talk.TalkManage;
import com.socialuni.center.web.service.comment.CommentAddLineTransfer;
import com.socialuni.center.web.store.CommentStore;
import com.socialuni.center.web.model.DO.comment.SocialCommentDO;
import com.socialuni.center.web.model.DO.talk.SocialTalkDO;
import com.socialuni.center.web.model.QO.community.comment.SocialCommentPostQO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialPostCommentEntity {
    @Resource
    private CommentStore commentStore;

    @Resource
    private TalkManage talkManage;

    public SocialCommentDO saveComment(SocialCommentPostQO addVO, Integer mineUserId) {
        //创建和保存comment到db
        SocialCommentDO commentDO = commentStore.saveAddComment(addVO, mineUserId);

        //关联更新talk到db，时间、评论次数等
        talkManage.updateTalkByAddComment(addVO.getTalkId());
        //设置为更新后的talk
        //关联更新comment到db，时间、评论次数等
        commentStore.updateCommentByAddComment(addVO);

        return commentDO;
    }
}
