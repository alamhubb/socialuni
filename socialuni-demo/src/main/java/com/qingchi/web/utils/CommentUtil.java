package com.qingchi.web.utils;


import com.socialuni.social.entity.model.DO.comment.CommentDO;
import com.socialuni.social.sdk.repository.CommentRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CommentUtil {
    private static CommentRepository commentRepository;

    @Resource
    public void setCommentRepository(CommentRepository commentRepository) {
        CommentUtil.commentRepository = commentRepository;
    }

    public static Integer getCommentIdByUnionId(String uid) {
        CommentDO commentDO = commentRepository.findOneBySocialuniUid(uid);
        if (commentDO != null) {
            return commentDO.getId();
        }
        return null;
    }
}
