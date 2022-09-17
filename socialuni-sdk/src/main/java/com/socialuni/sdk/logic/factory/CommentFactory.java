package com.socialuni.sdk.logic.factory;

import com.socialuni.sdk.model.QO.comment.SocialuniCommentPostQO;
import com.socialuni.sdk.utils.UnionIdUtil;
import com.socialuni.sdk.dao.DO.comment.SocialCommentDO;
import com.socialuni.sdk.dao.repository.CommentRepository;
import com.socialuni.sdk.constant.socialuni.ContentType;
import com.socialuni.sdk.constant.socialuni.ContentStatus;
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
    public SocialCommentDO createCommentDO(SocialuniCommentPostQO addQO, Integer requestUserId) {

        Integer talkId = UnionIdUtil.getUnionIdByUuidNotNull(addQO.getTalkId());

        Integer commentId = null;
        Integer replyCommentId = null;
        if (addQO.getCommentId() != null) {
            commentId = UnionIdUtil.getUnionIdByUuidNotNull(addQO.getCommentId());
            if (addQO.getReplyCommentId() != null) {
                replyCommentId = UnionIdUtil.getUnionIdByUuidNotNull(addQO.getReplyCommentId());
            }
        }
        //创建 do一步,获取序号
        SocialCommentDO commentNoDO = commentRepository.findFirstByTalkIdOrderByIdDesc(talkId);
        Integer commentNo = 0;
        if (commentNoDO != null) {
            commentNo = commentNoDO.getNo();
        }
        //构建DO，comment基础内容
        SocialCommentDO comment = new SocialCommentDO();
        comment.setNo(++commentNo);
        comment.setContent(addQO.getContent());
        comment.setStatus(ContentStatus.enable);
        comment.setReportContentType(ContentType.comment);
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
//        comment.setSocialuniUid(addVO.getSocialuniUid());

        Integer unionId = UnionIdUtil.createCommentUnionId();
        comment.setUnionId(unionId);

//        comment.setDevId(DevAccountUtils.getDevId());
//        comment.setThreeId(threeUnionId);
        return comment;
    }
}