package com.socialuni.center.web.domain.comment;

import com.socialuni.api.model.QO.comment.CenterCommentPostQO;
import com.socialuni.api.model.RO.talk.CenterCommentRO;
import com.socialuni.center.web.factory.RO.talk.CenterCommentROFactory;
import com.socialuni.center.web.utils.CenterUserUtil;
import com.socialuni.center.web.utils.UnionIdDbUtil;
import com.socialuni.social.sdk.domain.comment.SocialCommentPostDomain;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.QO.community.comment.SocialCommentPostQO;
import com.socialuni.social.model.model.RO.community.comment.SocialCommentRO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author qinkaiyuan
 * @date 2020-05-16 20:11
 */
@Service
@Slf4j
public class CenterCommentPostDomain {
    @Resource
    private SocialCommentPostDomain socialCommentPostDomain;

    public CenterCommentRO postComment(CenterCommentPostQO addQO) {
        UserDO mineUser = CenterUserUtil.getMineUserNotNull();

        Integer talkId = UnionIdDbUtil.getTalkIdByUnionId(addQO.getTalkId());

        Integer commentId = null;
        Integer replyCommentId = null;
        if (addQO.getCommentId() != null) {
            commentId = UnionIdDbUtil.getCommentIdByUid(addQO.getCommentId());
            if (addQO.getReplyCommentId() != null) {
                replyCommentId = UnionIdDbUtil.getCommentIdByUid(addQO.getReplyCommentId());
            }
        }

        SocialCommentPostQO socialCommentPostQO = new SocialCommentPostQO(addQO.getContent(), talkId, commentId, replyCommentId);

        SocialCommentRO socialCommentRO = socialCommentPostDomain.postComment(mineUser, socialCommentPostQO);

        CenterCommentRO commentRO = CenterCommentROFactory.getCommentRO(socialCommentRO, mineUser);

        return commentRO;
    }
}
