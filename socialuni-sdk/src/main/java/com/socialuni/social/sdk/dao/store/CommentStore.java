package com.socialuni.social.sdk.dao.store;

import com.socialuni.social.community.sdk.model.SocialuniCommentModel;
import com.socialuni.social.community.sdk.api.CommentInterface;
import com.socialuni.social.sdk.dao.utils.content.SocialuniCommentDOUtil;
import com.socialuni.social.sdk.logic.factory.CommentFactory;
import com.socialuni.social.sdk.model.QO.comment.SocialuniCommentPostQO;
import com.socialuni.social.sdk.facade.SocialuniUnionIdFacede;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class CommentStore {
    @Resource
    private CommentInterface commentApi;
    @Resource
    private CommentFactory commentFactory;

    //保存新增的comment
    public SocialuniCommentModel saveAddComment(SocialuniCommentPostQO addQO, Integer mineUserId) {
        SocialuniCommentModel commentDO = commentFactory.createCommentDO(
                addQO,
                mineUserId
        );

        //保存为预审核状态，关键词校验通过，改为正常
        commentDO = commentApi.savePut(commentDO);
        return commentDO;
    }


    /**
     * 修改comment时，关联更新回复评论和父评论，返回更新后的内容到comment中了
     *
     * @return
     */
    public void updateParentReplyCommentByAddComment(SocialuniCommentPostQO addVO) {
        if (addVO.getCommentId() == null) {
            return;
        }

        Integer commentId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(addVO.getCommentId());


        SocialuniCommentModel parentComment = SocialuniCommentDOUtil.getAllowNull(commentId);
        if (parentComment == null) {
            return;
        }
        Integer ChildCommentNum = parentComment.getChildCommentNum();
        //这里属于业务
        if (ChildCommentNum == null) {
            parentComment.setChildCommentNum(1);
        } else {
            parentComment.setChildCommentNum(++ChildCommentNum);
        }
        Date curDate = new Date();
        //只有直接回复父评论，才更新时间
        parentComment.setUpdateTime(curDate);

        if (addVO.getCommentId() != null) {
            Integer replyId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(addVO.getReplyCommentId());

            SocialuniCommentModel replyComment = SocialuniCommentDOUtil.getAllowNull(replyId);
            if (replyComment != null) {
                //测试所有自己评论自己，刚才处空指针了
                replyComment.setUpdateTime(curDate);
                //更新后保存到数据库
                commentApi.savePut(replyComment);
            }
        }
        //更新后保存到数据库
        commentApi.savePut(parentComment);
    }
}