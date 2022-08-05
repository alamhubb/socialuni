package com.socialuni.center.web.store;

import com.socialuni.center.web.factory.CommentFactory;
import com.socialuni.center.web.model.DO.comment.CommentDO;
import com.socialuni.center.web.repository.CommentRepository;
import com.socialuni.center.web.service.comment.CommentAddLineTransfer;
import com.socialuni.center.web.model.QO.community.comment.SocialCommentPostQO;
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
    public CommentDO saveAddComment(SocialCommentPostQO addVO, Integer mineUserId) {
        CommentDO commentDO = commentFactory.createCommentDO(
                addVO,
                mineUserId
        );

        //保存为预审核状态，关键词校验通过，改为正常
        commentDO = commentRepository.save(commentDO);
        return commentDO;
    }


    /**
     * 修改comment时，关联更新回复评论和父评论，返回更新后的内容到comment中了
     *
     * @param commentAddLineTransfer
     * @return
     */
    public CommentAddLineTransfer updateCommentByAddComment(CommentAddLineTransfer commentAddLineTransfer) {
        Date curDate = new Date();
        CommentDO parentComment = commentAddLineTransfer.getParentComment();
        //执行comment关联操作
        //为父评论添加子评论数
        if (parentComment != null) {
            Integer ChildCommentNum = parentComment.getChildCommentNum();
            //这里属于业务
            if (ChildCommentNum == null) {
                parentComment.setChildCommentNum(1);
            } else {
                parentComment.setChildCommentNum(++ChildCommentNum);
            }
            CommentDO replyComment = commentAddLineTransfer.getReplyComment();
            if (replyComment == null) {
                //只有直接回复父评论，才更新时间
                parentComment.setUpdateTime(curDate);
            } else {
                //测试所有自己评论自己，刚才处空指针了
                replyComment.setUpdateTime(curDate);
                //更新后保存到数据库
                replyComment = commentRepository.save(replyComment);
                commentAddLineTransfer.setReplyComment(replyComment);
            }
            //更新后保存到数据库
            parentComment = commentRepository.save(parentComment);
            commentAddLineTransfer.setParentComment(parentComment);
        }
        return commentAddLineTransfer;
    }
}
