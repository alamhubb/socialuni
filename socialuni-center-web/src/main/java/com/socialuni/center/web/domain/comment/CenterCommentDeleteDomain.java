package com.socialuni.center.web.domain.comment;

import com.socialuni.api.model.QO.comment.CenterCommentDeleteQO;
import com.socialuni.center.web.utils.CenterUserUtil;
import com.socialuni.center.web.utils.UnionIdDbUtil;
import com.socialuni.social.sdk.domain.comment.SocialCommentDeleteDomain;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.QO.community.comment.SocialCommentDeleteQO;
import com.socialuni.social.api.model.ResultRO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author qinkaiyuan
 * @date 2020-05-16 20:11
 */
@Service
@Slf4j
public class CenterCommentDeleteDomain {
    @Resource
    private SocialCommentDeleteDomain socialCommentDeleteDomain;

    /**
     * 删除动态操作，
     * 如果是系统管理员删除动态，则必须填写原因，删除后发表动态的用户将被封禁
     * 如果是自己删的自己的动态，则不需要填写原因，默认原因是用户自己删除
     */
    public ResultRO<Void> deleteComment(CenterCommentDeleteQO commentDeleteQO) {
        UserDO userDO = CenterUserUtil.getMineUserNotNull();

        Integer commentId = UnionIdDbUtil.getCommentIdByUid(commentDeleteQO.getCommentId());

        SocialCommentDeleteQO socialCommentDeleteQO = new SocialCommentDeleteQO(commentId);

        socialCommentDeleteDomain.deleteComment(userDO, socialCommentDeleteQO);
        return new ResultRO<>();
    }
}
