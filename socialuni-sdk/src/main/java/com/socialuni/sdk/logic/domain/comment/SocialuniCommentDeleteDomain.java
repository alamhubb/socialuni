package com.socialuni.sdk.logic.domain.comment;

import com.socialuni.sdk.constant.socialuni.ContentStatus;
import com.socialuni.sdk.dao.DO.comment.SocialCommentDO;
import com.socialuni.sdk.dao.DO.talk.SocialTalkDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.dao.repository.CommentRepository;
import com.socialuni.sdk.model.QO.comment.SocialuniCommentDeleteQO;
import com.socialuni.sdk.utils.SocialuniUserUtil;
import com.socialuni.sdk.utils.TalkUtils;
import com.socialuni.sdk.utils.UnionIdUtil;
import com.socialuni.social.web.sdk.exception.SocialParamsException;
import com.socialuni.social.web.sdk.model.ResultRO;
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
    private CommentRepository commentRepository;

    /**
     * 删除动态操作，
     * 如果是系统管理员删除动态，则必须填写原因，删除后发表动态的用户将被封禁
     * 如果是自己删的自己的动态，则不需要填写原因，默认原因是用户自己删除
     */
    public ResultRO<Void> deleteComment(SocialuniCommentDeleteQO commentDeleteQO) {
        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserNotNull();

        Integer commentId = UnionIdUtil.getUnionIdByUuidNotNull(commentDeleteQO.getCommentId());

        Optional<SocialCommentDO> optionalCommentDO = commentRepository.findOneByUnionIdAndStatusIn(commentId, ContentStatus.selfCanSeeContentStatus);
        if (!optionalCommentDO.isPresent()) {
            throw new SocialParamsException("评论已经删除");
        }
        SocialCommentDO commentDO = optionalCommentDO.get();
        SocialTalkDO talkDO = TalkUtils.getNotNull(commentDO.getTalkId());
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
        commentRepository.save(commentDO);
        return new ResultRO<>();
    }
}
