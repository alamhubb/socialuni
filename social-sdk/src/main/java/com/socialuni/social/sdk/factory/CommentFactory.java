package com.socialuni.social.sdk.factory;

import com.socialuni.social.constant.ContentType;
import com.socialuni.social.entity.model.DO.comment.CommentDO;
import com.socialuni.social.model.model.QO.community.comment.SocialCommentPostQO;
import com.socialuni.social.constant.ContentStatus;
import com.socialuni.social.sdk.repository.CommentRepository;
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
    public CommentDO createCommentDO(SocialCommentPostQO addVO, Integer requestUserId) {
        //创建 do一步,获取序号
        CommentDO commentNoDO = commentRepository.findFirstByTalkIdOrderByIdDesc(addVO.getTalkId());
        Integer commentNo = 0;
        if (commentNoDO != null) {
            commentNo = commentNoDO.getNo();
        }
        //构建DO，comment基础内容
        CommentDO comment = new CommentDO();
        comment.setNo(++commentNo);
        comment.setContent(addVO.getContent());
        comment.setStatus(ContentStatus.enable);
        comment.setReportContentType(ContentType.comment);
        comment.setHugNum(0);
        comment.setChildCommentNum(0);
        comment.setReportNum(0);
        Date curDate = new Date();
        comment.setCreateTime(curDate);
        comment.setUpdateTime(curDate);
        //关联内容
        comment.setReplyCommentId(addVO.getReplyCommentId());
        comment.setParentCommentId(addVO.getCommentId());
        comment.setTalkId(addVO.getTalkId());
        comment.setUserId(requestUserId);
        comment.setSocialuniUid(addVO.getSocialuniUid());
//        comment.setDevId(DevAccountUtils.getDevId());
//        comment.setThreeId(threeUnionId);
        return comment;
    }
}