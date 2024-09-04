package com.socialuni.social.community.sdk.logic.factory;

import com.socialuni.social.community.sdk.dao.DO.SocialuniCommentDO;
import com.socialuni.social.community.sdk.model.QO.comment.SocialuniCommentPostQO;
import com.socialuni.social.community.sdk.repository.CommentRepository;
import com.socialuni.social.common.api.enumeration.ContentStatus;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.content.utils.SocialuniTextContentUtil;
import com.socialuni.social.common.api.constant.SocialuniContentType;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class CommentFactory {
    @Resource
    private CommentRepository commentApi;

    /***
     *
     * @param addQO
     * @param requestUserId
     * @return
     */
    public SocialuniCommentDO createCommentDO(SocialuniCommentPostQO addQO, Integer requestUserId) {

        Integer talkId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(addQO.getTalkId());

        Integer commentId = null;
        Integer replyCommentId = null;
        if (addQO.getCommentId() != null) {
            commentId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(addQO.getCommentId());
            if (addQO.getReplyCommentId() != null) {
                replyCommentId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(addQO.getReplyCommentId());
            }
        }
        //创建 do一步,获取序号
        SocialuniCommentDO commentNoDO = commentApi.findFirstByTalkIdOrderByIdDesc(talkId);
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
        SocialuniTextContentUtil.setHasUnderageAndContactInfoByContentText(comment.getContent(), comment);
//        comment.setSocialuniUid(addVO.getSocialuniUid());

        Long unionId = SocialuniUnionIdFacede.createCommentUnionId();
        comment.setUnionId(unionId);

//        comment.setDevId(DevAccountUtils.getDevId());
//        comment.setThreeId(threeUnionId);
        return comment;
    }
}