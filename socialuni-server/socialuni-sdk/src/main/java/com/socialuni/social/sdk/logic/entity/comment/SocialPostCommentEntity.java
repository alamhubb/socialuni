package com.socialuni.social.sdk.logic.entity.comment;

import com.socialuni.social.community.sdk.dao.DO.SocialuniCommentDO;
import com.socialuni.social.sdk.dao.store.CommentStore;
import com.socialuni.social.sdk.logic.manage.talk.TalkManage;
import com.socialuni.social.sdk.model.QO.comment.SocialuniCommentPostQO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
public class SocialPostCommentEntity {
    @Resource
    private CommentStore commentStore;

    @Resource
    private TalkManage talkManage;


    @Transactional
    public SocialuniCommentDO saveComment(SocialuniCommentPostQO addVO, Integer mineUserId) {
        //创建和保存comment到db
        SocialuniCommentDO commentDO = commentStore.saveAddComment(addVO, mineUserId);

        //关联更新talk到db，时间、评论次数等
        talkManage.updateTalkByAddComment(addVO.getTalkId());
        //设置为更新后的talk
        //关联更新comment到db，时间、评论次数等
        if (addVO.getCommentId() != null) {
            commentStore.updateParentReplyCommentByAddComment(addVO);
        }

        return commentDO;
    }
}
