package com.qingchi.web.domain.comment;

import com.qingchi.web.utils.CommentUtil;
import com.socialuni.api.feignAPI.SocialuniCommentAPI;
import com.socialuni.api.model.QO.comment.CenterCommentDeleteQO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.QO.community.comment.SocialCommentDeleteQO;
import com.socialuni.social.sdk.domain.comment.SocialCommentDeleteDomain;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author qinkaiyuan
 * @date 2020-05-16 20:11
 */
@Service
@Slf4j
public class CommentDeleteDomain {
    @Resource
    private SocialCommentDeleteDomain socialCommentDeleteDomain;
    @Resource
    SocialuniCommentAPI socialuniCommentAPI;

    public void deleteComment(CenterCommentDeleteQO commentDeleteQO) {
        // 先确认本条应用是否为本应用的动态
        socialuniCommentAPI.deleteComment(commentDeleteQO);

        Integer commentId = CommentUtil.getCommentIdByUnionId(commentDeleteQO.getCommentId());

        UserDO mineUser = SocialUserUtil.getMineUserAllowNull();

        SocialCommentDeleteQO socialCommentDeleteQO = new SocialCommentDeleteQO(commentId);

        socialCommentDeleteDomain.deleteComment(mineUser, socialCommentDeleteQO);
    }
}
