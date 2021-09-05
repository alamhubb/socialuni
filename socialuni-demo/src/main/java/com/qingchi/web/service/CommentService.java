package com.qingchi.web.service;

import com.qingchi.web.domain.comment.CommentDeleteDomain;
import com.qingchi.web.domain.comment.CommentPostDomain;
import com.socialuni.api.model.QO.comment.CenterCommentDeleteQO;
import com.socialuni.api.model.QO.comment.CenterCommentPostQO;
import com.socialuni.api.model.RO.talk.CenterCommentRO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CommentService {
    @Resource
    CommentPostDomain commentPostDomain;
    @Resource
    CommentDeleteDomain commentDeleteDomain;

    public ResultRO<CenterCommentRO> postComment(CenterCommentPostQO commentQO) {
        CenterCommentRO commentRO = commentPostDomain.postComment(commentQO);
        return new ResultRO<>(commentRO);
    }

    public ResultRO<Void> deleteComment(CenterCommentDeleteQO commentDeleteQO) {
        commentDeleteDomain.deleteComment(commentDeleteQO);
        return ResultRO.success();
    }
}
