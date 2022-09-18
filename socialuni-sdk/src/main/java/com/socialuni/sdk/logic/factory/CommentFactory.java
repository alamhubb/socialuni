package com.socialuni.sdk.logic.factory;

import com.socialuni.sdk.constant.socialuni.ContentStatus;
import com.socialuni.sdk.constant.socialuni.SocialuniContentType;
import com.socialuni.sdk.dao.DO.community.comment.SocialuniCommentDO;
import com.socialuni.sdk.dao.repository.CommentRepository;
import com.socialuni.sdk.logic.service.content.SocialuniTextContentUtil;
import com.socialuni.sdk.model.QO.comment.SocialuniCommentPostQO;
import com.socialuni.sdk.utils.SocialuniUnionIdUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class CommentFactory {
    @Resource
    private CommentRepository commentRepository;

    /***
     *
     * @param addVO
     * @param requestUserId
     * @return
     */
    public SocialuniCommentDO createCommentDO(SocialuniCommentPostQO addQO, Integer requestUserId) {

        Integer talkId = SocialuniUnionIdUtil.getUnionIdByUuidNotNull(addQO.getTalkId());

        Integer commentId = null;
        Integer replyCommentId = null;
        if (addQO.getCommentId() != null) {
            commentId = SocialuniUnionIdUtil.getUnionIdByUuidNotNull(addQO.getCommentId());
            if (addQO.getReplyCommentId() != null) {
                replyCommentId = SocialuniUnionIdUtil.getUnionIdByUuidNotNull(addQO.getReplyCommentId());
            }
        }
        //创建 do一步,获取序号
        SocialuniCommentDO commentNoDO = commentRepository.findFirstByTalkIdOrderByIdDesc(talkId);
        Integer commentNo = 0;
        if (commentNoDO != null) {
            commentNo = commentNoDO.getNo();
        }
        //构建DO，comment基础内容
        SocialuniCommentDO comment = new SocialuniCommentDO();
        comment.setNo(++commentNo);
        comment.setContent(addQO.getContent());
        comment.setStatus(ContentStatus.enable);
        comment.setContentType(SocialuniContentType.comment);
        comment.setHugNum(0);
        comment.setChildCommentNum(0);
        comment.setReportNum(0);
        Date curDate = new Date();
        comment.setCreateTime(curDate);
        comment.setUpdateTime(curDate);
        //关联内容
        comment.setReplyCommentId(replyCommentId);
        comment.setParentCommentId(commentId);
        comment.setTalkId(talkId);
        comment.setUserId(requestUserId);
        comment.setHasContactInfo(false);
        comment.setHasUnderageContent(false);
        SocialuniTextContentUtil.setHasUnderageAndContactInfoByContentText(comment);
//        comment.setSocialuniUid(addVO.getSocialuniUid());

        Integer unionId = SocialuniUnionIdUtil.createCommentUnionId();
        comment.setUnionId(unionId);

//        comment.setDevId(DevAccountUtils.getDevId());
//        comment.setThreeId(threeUnionId);
        return comment;
    }
}