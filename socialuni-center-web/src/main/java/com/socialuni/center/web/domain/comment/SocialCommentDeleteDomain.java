package com.socialuni.center.web.domain.comment;

import com.socialuni.social.constant.ContentStatus;
import com.socialuni.social.exception.SocialParamsException;
import com.socialuni.center.web.model.DO.comment.SocialCommentDO;
import com.socialuni.center.web.model.DO.talk.SocialTalkDO;
import com.socialuni.center.web.model.DO.user.SocialUserDO;
import com.socialuni.center.web.repository.CommentRepository;
import com.socialuni.center.web.utils.TalkUtils;
import com.socialuni.center.web.model.QO.community.comment.SocialCommentDeleteQO;
import com.socialuni.social.api.model.ResultRO;
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
public class SocialCommentDeleteDomain {
    @Resource
    private CommentRepository commentRepository;

    /**
     * 删除动态操作，
     * 如果是系统管理员删除动态，则必须填写原因，删除后发表动态的用户将被封禁
     * 如果是自己删的自己的动态，则不需要填写原因，默认原因是用户自己删除
     */
    public ResultRO<Void> deleteComment(SocialUserDO mineUser, SocialCommentDeleteQO commentDeleteQO) {
        Optional<SocialCommentDO> optionalCommentDO = commentRepository.findOneByIdAndStatusIn(commentDeleteQO.getCommentId(), ContentStatus.otherCanSeeContentStatus);
        if (!optionalCommentDO.isPresent()) {
            throw new SocialParamsException("评论已经删除");
        }
        SocialCommentDO commentDO = optionalCommentDO.get();
        SocialTalkDO talkDO = TalkUtils.get(commentDO.getTalkId());
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
