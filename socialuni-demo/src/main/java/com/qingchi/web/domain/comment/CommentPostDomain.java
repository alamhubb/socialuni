package com.qingchi.web.domain.comment;

import com.qingchi.web.utils.CommentUtil;
import com.qingchi.web.utils.TalkUtil;
import com.socialuni.api.feignAPI.SocialuniCommentAPI;
import com.socialuni.api.model.QO.comment.CenterCommentPostQO;
import com.socialuni.api.model.RO.talk.CenterCommentRO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.QO.community.comment.SocialCommentPostQO;
import com.socialuni.social.sdk.domain.comment.SocialCommentPostDomain;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class CommentPostDomain {
    @Resource
    SocialCommentPostDomain socialCommentPostDomain;
    @Resource
    SocialuniCommentAPI socialuniCommentAPI;

    public CenterCommentRO postComment(CenterCommentPostQO commentPostQO) {
        UserDO mineUser = SocialUserUtil.getMineUserAllowNull();
        //在社交联盟中添加动态
        ResultRO<CenterCommentRO> centerTalkROResultRO = socialuniCommentAPI.postComment(commentPostQO);
        CenterCommentRO centerCommentRO = centerTalkROResultRO.getData();


        Integer talkId = TalkUtil.getTalkIdByUnionId(commentPostQO.getTalkId());
        if (talkId == null) {
            return centerCommentRO;
        }
        String commentUid = commentPostQO.getCommentId();
        Integer commentId = null;
        if (!StringUtils.isEmpty(commentUid)) {
            commentId = CommentUtil.getCommentIdByUnionId(commentUid);
            if (commentId == null) {
                return centerCommentRO;
            }
        }
        String replyCommentUid = commentPostQO.getReplyCommentId();
        Integer replyCommentId = null;
        if (!StringUtils.isEmpty(replyCommentUid)) {
            replyCommentId = CommentUtil.getCommentIdByUnionId(replyCommentUid);
            if (replyCommentId == null) {
                return centerCommentRO;
            }
        }

        SocialCommentPostQO socialCommentPostQO = new SocialCommentPostQO(commentPostQO.getContent(), talkId, commentId, replyCommentId);
        //设置社交联盟中的唯一id，便于以后确认此动态是否属于本应用
        socialCommentPostQO.setSocialuniUid(centerCommentRO.getId());
        socialCommentPostDomain.postComment(mineUser, socialCommentPostQO);
        return centerCommentRO;
    }
}
