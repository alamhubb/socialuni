package com.socialuni.sdk.dao.store;

import com.socialuni.sdk.logic.factory.CommentFactory;
import com.socialuni.sdk.dao.DO.comment.SocialCommentDO;
import com.socialuni.sdk.dao.repository.CommentRepository;
import com.socialuni.sdk.model.QO.comment.SocialuniCommentPostQO;
import com.socialuni.sdk.utils.CommentUtils;
import com.socialuni.sdk.utils.UnionIdUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class CommentStore {
    @Resource
    private CommentRepository commentRepository;
    @Resource
    private CommentFactory commentFactory;

    //保存新增的comment
    public SocialCommentDO saveAddComment(SocialuniCommentPostQO addQO, Integer mineUserId) {
        SocialCommentDO commentDO = commentFactory.createCommentDO(
                addQO,
                mineUserId
        );

        //保存为预审核状态，关键词校验通过，改为正常
        commentDO = commentRepository.save(commentDO);
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

        Integer commentId  = UnionIdUtil.getUnionIdByUuidNotNull(addVO.getCommentId());


        SocialCommentDO parentComment = CommentUtils.getAllowNull(commentId);
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
            Integer replyId = UnionIdUtil.getUnionIdByUuidNotNull(addVO.getReplyCommentId());

            SocialCommentDO replyComment = CommentUtils.getAllowNull(replyId);
            if (replyComment != null) {
                //测试所有自己评论自己，刚才处空指针了
                replyComment.setUpdateTime(curDate);
                //更新后保存到数据库
                commentRepository.save(replyComment);
            }
        }
        //更新后保存到数据库
        commentRepository.save(parentComment);
    }
}