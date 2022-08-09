package com.socialuni.center.web.store;

import com.socialuni.center.web.factory.CommentFactory;
import com.socialuni.center.web.model.DO.comment.SocialCommentDO;
import com.socialuni.center.web.model.QO.community.comment.SocialCommentPostQO;
import com.socialuni.center.web.repository.CommentRepository;
import com.socialuni.center.web.utils.CommentUtils;
import com.socialuni.center.web.utils.UnionIdDbUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CommentStore {
    @Resource
    private CommentRepository commentRepository;
    @Resource
    private CommentFactory commentFactory;

    //保存新增的comment
    public SocialCommentDO saveAddComment(SocialCommentPostQO addVO, Integer mineUserId) {
        SocialCommentDO commentDO = commentFactory.createCommentDO(
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
     * @return
     */
    public void updateCommentByAddComment(SocialCommentPostQO addVO) {
        if (addVO.getCommentId() == null || UnionIdDbUtil.notSelfData(addVO.getCommentId())) {
            return;
        }
        SocialCommentDO parentComment = CommentUtils.get(addVO.getCommentId());
        Integer ChildCommentNum = parentComment.getChildCommentNum();
        //这里属于业务
        if (ChildCommentNum == null) {
            parentComment.setChildCommentNum(1);
        } else {
            parentComment.setChildCommentNum(++ChildCommentNum);
        }
        //更新后保存到数据库
        commentRepository.save(parentComment);
    }
}
