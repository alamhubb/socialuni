package com.socialuni.sdk.factory;

import com.socialuni.sdk.utils.UnionIdDbUtil;
import com.socialuni.sdk.dao.DO.comment.SocialCommentDO;
import com.socialuni.sdk.model.QO.community.comment.SocialCommentPostQO;
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
    public SocialCommentDO createCommentDO(SocialCommentPostQO addVO, Integer requestUserId) {
        //创建 do一步,获取序号
        SocialCommentDO commentNoDO = commentRepository.findFirstByTalkIdOrderByIdDesc(addVO.getTalkId());
        Integer commentNo = 0;
        if (commentNoDO != null) {
            commentNo = commentNoDO.getNo();
        }
        //构建DO，comment基础内容
        SocialCommentDO comment = new SocialCommentDO();
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

        Integer unionId = UnionIdDbUtil.createCommentUnionId();
        comment.setUnionId(unionId);

//        comment.setDevId(DevAccountUtils.getDevId());
//        comment.setThreeId(threeUnionId);
        return comment;
    }
}