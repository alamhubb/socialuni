package com.socialuni.center.web.entity.comment;

import com.socialuni.center.web.manage.talk.TalkManage;
import com.socialuni.center.web.service.comment.CommentAddLineTransfer;
import com.socialuni.center.web.store.CommentStore;
import com.socialuni.social.entity.model.DO.comment.CommentDO;
import com.socialuni.social.entity.model.DO.talk.TalkDO;
import com.socialuni.social.model.model.QO.community.comment.SocialCommentPostQO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialPostCommentEntity {
    @Resource
    private CommentStore commentStore;

    @Resource
    private TalkManage talkManage;

    public CommentAddLineTransfer saveComment(SocialCommentPostQO addVO, CommentAddLineTransfer commentAddLineTransfer, Integer mineUserId) {
        //创建和保存comment到db
        CommentDO commentDO = commentStore.saveAddComment(addVO, mineUserId);

        //关联更新talk到db，时间、评论次数等
        TalkDO talkDO = talkManage.updateTalkByAddComment(commentAddLineTransfer.getTalk());
        //设置为更新后的talk
        commentAddLineTransfer.setTalk(talkDO);
        //关联更新comment到db，时间、评论次数等
        commentAddLineTransfer = commentStore.updateCommentByAddComment(commentAddLineTransfer);

        commentAddLineTransfer.setCommentDO(commentDO);

        return commentAddLineTransfer;
    }
}
