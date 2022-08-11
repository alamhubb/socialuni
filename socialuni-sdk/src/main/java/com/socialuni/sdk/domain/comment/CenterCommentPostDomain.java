package com.socialuni.sdk.domain.comment;

import com.socialuni.sdk.factory.RO.talk.CenterCommentROFactory;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.QO.comment.CenterCommentPostQO;
import com.socialuni.sdk.model.QO.community.comment.SocialCommentPostQO;
import com.socialuni.sdk.model.RO.community.comment.SocialCommentRO;
import com.socialuni.sdk.model.RO.talk.CenterCommentRO;
import com.socialuni.sdk.utils.CenterUserUtil;
import com.socialuni.sdk.utils.UnionIdDbUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * @author qinkaiyuan
 * @date 2020-05-16 20:11
 */
@Service
@Slf4j
public class CenterCommentPostDomain {
    @Resource
    private SocialCommentPostDomain socialCommentPostDomain;

    @Transactional
    public CenterCommentRO postComment(CenterCommentPostQO addQO) {
        SocialUserDO mineUser = CenterUserUtil.getMineUserNotNull();

        Integer talkId = UnionIdDbUtil.getTalkUnionIdByUidNotNull(addQO.getTalkId());

        Integer commentId = null;
        Integer replyCommentId = null;
        if (addQO.getCommentId() != null) {
            commentId = UnionIdDbUtil.getCommentUnionIdByUidNotNull(addQO.getCommentId());
            if (addQO.getReplyCommentId() != null) {
                replyCommentId = UnionIdDbUtil.getCommentUnionIdByUidNotNull(addQO.getReplyCommentId());
            }
        }

        SocialCommentPostQO socialCommentPostQO = new SocialCommentPostQO(addQO.getContent(), talkId, commentId, replyCommentId);

        SocialCommentRO socialCommentRO = socialCommentPostDomain.postComment(mineUser, socialCommentPostQO);

        CenterCommentRO commentRO = CenterCommentROFactory.getCommentRO(socialCommentRO, mineUser);

        return commentRO;
    }
}
