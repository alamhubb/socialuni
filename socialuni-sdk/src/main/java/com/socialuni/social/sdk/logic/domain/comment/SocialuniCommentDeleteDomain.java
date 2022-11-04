package com.socialuni.social.sdk.logic.domain.comment;

import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.sdk.model.ResultRO;
import com.socialuni.social.community.sdk.entity.SocialuniCommentDO;
import com.socialuni.social.community.sdk.entity.SocialuniTalkDO;
import com.socialuni.social.community.sdk.repository.CommentRepository;
import com.socialuni.social.sdk.constant.socialuni.ContentStatus;
import com.socialuni.social.sdk.dao.utils.content.SocialuniTalkDOUtil;
import com.socialuni.social.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.sdk.model.QO.comment.SocialuniCommentDeleteQO;
import com.socialuni.social.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.user.sdk.entity.SocialuniUserDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Optional;

/**
 * @author qinkaiyuan
 * @date 2020-05-16 20:11
 */
@Service
@Slf4j
public class SocialuniCommentDeleteDomain {
    @Resource
    private CommentRepository commentApi;

    /**
     * 删除动态操作，
     * 如果是系统管理员删除动态，则必须填写原因，删除后发表动态的用户将被封禁
     * 如果是自己删的自己的动态，则不需要填写原因，默认原因是用户自己删除
     */
    public ResultRO<Void> deleteComment(SocialuniCommentDeleteQO commentDeleteQO) {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();

        Integer commentId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(commentDeleteQO.getCommentId());

        Optional<?  extends SocialuniCommentDO> optionalCommentDO = commentApi.findOneByUnionIdAndStatusIn(commentId, ContentStatus.selfCanSeeContentStatus);
        if (!optionalCommentDO.isPresent()) {
            throw new SocialParamsException("评论已经删除");
        }
        SocialuniCommentDO commentDO = optionalCommentDO.get();
        SocialuniTalkDO talkDO = SocialuniTalkDOUtil.getTalkNotNull(commentDO.getTalkId());
        //是否是自己删除自己的动态
        if (commentDO.getUserId().equals(mineUser.getUnionId())) {
            commentDO.setStatus(ContentStatus.delete);
            commentDO.setDeleteReason("评论用户自行删除");
        } else if (talkDO.getUserId().equals(mineUser.getUnionId())) {
            commentDO.setStatus(ContentStatus.delete);
            commentDO.setDeleteReason("动态用户删除评论");
        } else {
            throw new SocialParamsException("无法删除不属于自己的评论");
        }
        commentDO.setUpdateTime(new Date());
        commentApi.savePut(commentDO);
        return new ResultRO<>();
    }
}
