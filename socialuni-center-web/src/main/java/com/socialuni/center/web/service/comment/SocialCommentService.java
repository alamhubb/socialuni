package com.socialuni.center.web.service.comment;


import com.socialuni.center.web.domain.comment.SocialCommentDeleteDomain;
import com.socialuni.center.web.domain.comment.SocialCommentPostDomain;
import com.socialuni.center.web.model.DO.user.UserDO;
import com.socialuni.center.web.utils.SocialUserUtil;
import com.socialuni.center.web.model.QO.community.comment.SocialCommentDeleteQO;
import com.socialuni.center.web.model.QO.community.comment.SocialCommentPostQO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.center.web.model.RO.community.comment.SocialCommentRO;
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
        UserDO mineUser = SocialUserUtil.getMineUserAllowNull();

        SocialCommentRO socialCommentRO = socialPostCommentDomain.postComment(mineUser, socialCommentPostQO);

        return new ResultRO<>(socialCommentRO);
    }

    public ResultRO<Void> deleteComment(SocialCommentDeleteQO commentDeleteQO) {
        UserDO mineUser = SocialUserUtil.getMineUserAllowNull();
        socialCommentDeleteDomain.deleteComment(mineUser, commentDeleteQO);
        return new ResultRO<>();
    }
}
