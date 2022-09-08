package com.socialuni.sdk.logic.service.comment;


import com.socialuni.sdk.logic.domain.comment.SocialCommentDeleteDomain;
import com.socialuni.sdk.logic.domain.comment.SocialCommentPostDomain;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.utils.SocialUserUtil;
import com.socialuni.sdk.model.QO.community.comment.SocialCommentDeleteQO;
import com.socialuni.sdk.model.QO.community.comment.SocialCommentPostQO;
import com.socialuni.social.web.sdk.model.ResultRO;
import com.socialuni.sdk.model.RO.community.comment.SocialCommentRO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author qinkaiyuan
 * @date 2020-05-16 20:11
 */
@Service
@Slf4j
public class SocialCommentService {
    @Resource
    SocialCommentPostDomain socialPostCommentDomain;
    @Resource
    SocialCommentDeleteDomain socialCommentDeleteDomain;

    public ResultRO<SocialCommentRO> postComment(SocialCommentPostQO socialCommentPostQO) {
        SocialUserDO mineUser = SocialUserUtil.getMineUserAllowNull();

        SocialCommentRO socialCommentRO = socialPostCommentDomain.postComment(mineUser, socialCommentPostQO);

        return new ResultRO<>(socialCommentRO);
    }

    public ResultRO<Void> deleteComment(SocialCommentDeleteQO commentDeleteQO) {
        SocialUserDO mineUser = SocialUserUtil.getMineUserAllowNull();
        socialCommentDeleteDomain.deleteComment(mineUser, commentDeleteQO);
        return new ResultRO<>();
    }
}
